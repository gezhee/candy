/*   
 *   A   JavaScript   implementation   of   the   Secure   Hash   Algorithm,   SHA-1,   as   defined   
 *   in   FIPS   PUB   180-1   
 *   Version   2.1-BETA   Copyright   Paul   Johnston   2000   -   2002.   
 *   Other   contributors:   Greg   Holt,   Andrew   Kepert,   Ydnar,   Lostinet   
 *   Distributed   under   the   BSD   License   
 *   See   http://pajhome.org.uk/crypt/md5   for   details.   
 */
/*   
 *   Configurable   variables.   You   may   need   to   tweak   these   to   be   compatible   with   
 *   the   server-side,   but   the   defaults   work   in   most   cases.   
 */
var hexcase = 0; /*   hex   output   format.   0   -   lowercase;   1   -   uppercase                 */
var b64pad = ""; /*   base-64   pad   character.   "="   for   strict   RFC   compliance       */
var chrsz = 8; /*   bits   per   input   character.   8   -   ASCII;   16   -   Unicode             */

/*   
 *   These   are   the   functions   you'll   usually   want   to   call   
 *   They   take   string   arguments   and   return   either   hex   or   base-64   encoded   strings   
 */
function hex_sha1(s) {
	return binb2hex(core_sha1(str2binb(s), s.length * chrsz));
}

/*   
 *   Calculate   the   SHA-1   of   an   array   of   big-endian   words,   and   a   bit   length   
 */
function core_sha1(x, len) {
	/*   append   padding   */
	x[len >> 5] |= 0x80 << (24 - len % 32);
	x[((len + 64 >> 9) << 4) + 15] = len;

	var w = Array(80);
	var a = 1732584193;
	var b = -271733879;
	var c = -1732584194;
	var d = 271733878;
	var e = -1009589776;

	for (var i = 0; i < x.length; i += 16) {
		var olda = a;
		var oldb = b;
		var oldc = c;
		var oldd = d;
		var olde = e;

		for (var j = 0; j < 80; j++) {
			if (j < 16)
				w[j] = x[i + j];
			else
				w[j] = rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
			var t = safe_add(safe_add(rol(a, 5), sha1_ft(j, b, c, d)),
					safe_add(safe_add(e, w[j]), sha1_kt(j)));
			e = d;
			d = c;
			c = rol(b, 30);
			b = a;
			a = t;
		}

		a = safe_add(a, olda);
		b = safe_add(b, oldb);
		c = safe_add(c, oldc);
		d = safe_add(d, oldd);
		e = safe_add(e, olde);
	}

	return Array(a, b, c, d, e);

}

/*   
 *   Perform   the   appropriate   triplet   combination   function   for   the   current   
 *   iteration   
 */
function sha1_ft(t, b, c, d) {
	if (t < 20)
		return (b & c) | ((~b) & d);
	if (t < 40)
		return b ^ c ^ d;
	if (t < 60)
		return (b & c) | (b & d) | (c & d);
	return b ^ c ^ d;
}

/*   
 *   Determine   the   appropriate   additive   constant   for   the   current   iteration   
 */
function sha1_kt(t) {
	return (t < 20) ? 1518500249 : (t < 40) ? 1859775393
			: (t < 60) ? -1894007588 : -899497514;
}

/*   
 *   Calculate   the   HMAC-SHA1   of   a   key   and   some   data   
 */
function core_hmac_sha1(key, data) {
	var bkey = str2binb(key);
	if (bkey.length > 16)
		bkey = core_sha1(bkey, key.length * chrsz);

	var ipad = Array(16), opad = Array(16);
	for (var i = 0; i < 16; i++) {
		ipad[i] = bkey[i] ^ 0x36363636;
		opad[i] = bkey[i] ^ 0x5C5C5C5C;
	}

	var hash = core_sha1(ipad.concat(str2binb(data)), 512 + data.length * chrsz);
	return core_sha1(opad.concat(hash), 512 + 160);
}

/*   
 *   Add   integers,   wrapping   at   2^32.   This   uses   16-bit   operations   internally   
 *   to   work   around   bugs   in   some   JS   interpreters.   
 */
function safe_add(x, y) {
	var lsw = (x & 0xFFFF) + (y & 0xFFFF);
	var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
	return (msw << 16) | (lsw & 0xFFFF);
}

/*   
 *   Bitwise   rotate   a   32-bit   number   to   the   left.   
 */
function rol(num, cnt) {
	return (num << cnt) | (num >>> (32 - cnt));
}

/*   
 *   Convert   an   8-bit   or   16-bit   string   to   an   array   of   big-endian   words   
 *   In   8-bit   function,   characters   >255   have   their   hi-byte   silently   ignored.   
 */
function str2binb(str) {
	var bin = Array();
	var mask = (1 << chrsz) - 1;
	for (var i = 0; i < str.length * chrsz; i += chrsz)
		bin[i >> 5] |= (str.charCodeAt(i / chrsz) & mask) << (24 - i % 32);
	return bin;
}

/*   
 *   Convert   an   array   of   big-endian   words   to   a   string   
 */
function binb2str(bin) {
	var str = "";
	var mask = (1 << chrsz) - 1;
	for (var i = 0; i < bin.length * 32; i += chrsz)
		str += String.fromCharCode((bin[i >> 5] >>> (24 - i % 32)) & mask);
	return str;
}

/*   
 *   Convert   an   array   of   big-endian   words   to   a   hex   string.   
 */
function binb2hex(binarray) {
	var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
	var str = "";
	for (var i = 0; i < binarray.length * 4; i++) {
		str += hex_tab
				.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8 + 4)) & 0xF)
				+ hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8)) & 0xF);
	}
	return str;
}

/*   
 *   Convert   an   array   of   big-endian   words   to   a   base-64   string   
 */
function binb2b64(binarray) {
	var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var str = "";
	for (var i = 0; i < binarray.length * 4; i += 3) {
		var triplet = (((binarray[i >> 2] >> 8 * (3 - i % 4)) & 0xFF) << 16)
				| (((binarray[i + 1 >> 2] >> 8 * (3 - (i + 1) % 4)) & 0xFF) << 8)
				| ((binarray[i + 2 >> 2] >> 8 * (3 - (i + 2) % 4)) & 0xFF);
		for (var j = 0; j < 4; j++) {
			if (i * 8 + j * 6 > binarray.length * 32)
				str += b64pad;
			else
				str += tab.charAt((triplet >> 6 * (3 - j)) & 0x3F);
		}
	}
	return str;
}

/*
 * Convert an UTF-16 to UTF-8
 */
function utf16to8(str) {
	var out, i, len, c;

	out = "";
	len = str.length;
	for (i = 0; i < len; i++) {
		c = str.charCodeAt(i);
		if ((c >= 0x0001) && (c <= 0x007F)) {
			out += str.charAt(i);
		} else if (c > 0x07FF) {
			out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
			out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		} else {
			out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		}
	}
	return out;
}

// ----------------------------------

jQuery.extend({
			createUploadIframe : function(id, uri) {
				//create frame
				var frameId = 'jUploadFrame' + id;
				var iframeHtml = '<iframe id="'
						+ frameId
						+ '" name="'
						+ frameId
						+ '" style="position:absolute; top:-9999px; left:-9999px"';
				if (window.ActiveXObject) {
					if (typeof uri == 'boolean') {
						iframeHtml += ' src="' + 'javascript:false' + '"';

					} else if (typeof uri == 'string') {
						iframeHtml += ' src="' + uri + '"';

					}
				}
				iframeHtml += ' />';
				jQuery(iframeHtml).appendTo(document.body);

				return jQuery('#' + frameId).get(0);
			},
			createUploadForm : function(id, fileElementId, data) {
				//create form	
				var formId = 'jUploadForm' + id;
				var fileId = 'jUploadFile' + id;
				var form = jQuery('<form  action="" method="POST" name="'
						+ formId + '" id="' + formId
						+ '" enctype="multipart/form-data"></form>');
				if (data) {
					for ( var i in data) {
						jQuery(
								'<input type="hidden" name="' + i + '" value="'
										+ data[i] + '" />').appendTo(form);
					}
				}
				var oldElement = jQuery('#' + fileElementId);
				var newElement = jQuery(oldElement).clone();
				jQuery(oldElement).attr('id', fileId);
				jQuery(oldElement).before(newElement);
				jQuery(oldElement).appendTo(form);

				//set attributes
				jQuery(form).css('position', 'absolute');
				jQuery(form).css('top', '-1200px');
				jQuery(form).css('left', '-1200px');
				jQuery(form).appendTo('body');
				return form;
			},

			ajaxFileUpload : function(s) {
				// TODO introduce global settings, allowing the client to modify them for all requests, not only timeout		
				s = jQuery.extend({}, jQuery.ajaxSettings, s);
				var id = new Date().getTime();
				var form = jQuery.createUploadForm(id, s.fileElementId,
						(typeof (s.data) == 'undefined' ? false : s.data));
				var io = jQuery.createUploadIframe(id, s.secureuri);
				var frameId = 'jUploadFrame' + id;
				var formId = 'jUploadForm' + id;
				// Watch for a new set of requests
				if (s.global && !jQuery.active++) {
					jQuery.event.trigger("ajaxStart");
				}
				var requestDone = false;
				// Create the request object
				var xml = {};
				if (s.global)
					jQuery.event.trigger("ajaxSend", [ xml, s ]);
				// Wait for a response to come back
				var uploadCallback = function(isTimeout) {
					var io = document.getElementById(frameId);
					try {
						if (io.contentWindow) {
							xml.responseText = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML
									: null;
							xml.responseXML = io.contentWindow.document.XMLDocument ? io.contentWindow.document.XMLDocument
									: io.contentWindow.document;

						} else if (io.contentDocument) {
							xml.responseText = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML
									: null;
							xml.responseXML = io.contentDocument.document.XMLDocument ? io.contentDocument.document.XMLDocument
									: io.contentDocument.document;
						}
					} catch (e) {
						jQuery.handleError(s, xml, null, e);
					}
					if (xml || isTimeout == "timeout") {
						requestDone = true;
						var status;
						try {
							status = isTimeout != "timeout" ? "success"
									: "error";
							// Make sure that the request was successful or notmodified
							if (status != "error") {
								// process the data (runs the xml through httpData regardless of callback)
								var data = jQuery.uploadHttpData(xml,
										s.dataType);
								// If a local callback was specified, fire it and pass it the data
								if (s.success)
									s.success(data, status);

								// Fire the global callback
								if (s.global)
									jQuery.event.trigger("ajaxSuccess", [ xml,
											s ]);
							} else
								jQuery.handleError(s, xml, status);
						} catch (e) {
							status = "error";
							jQuery.handleError(s, xml, status, e);
						}

						// The request was completed
						if (s.global)
							jQuery.event.trigger("ajaxComplete", [ xml, s ]);

						// Handle the global AJAX counter
						if (s.global && !--jQuery.active)
							jQuery.event.trigger("ajaxStop");

						// Process result
						if (s.complete)
							s.complete(xml, status);

						jQuery(io).unbind();

						setTimeout(function() {
							try {
								jQuery(io).remove();
								jQuery(form).remove();

							} catch (e) {
								jQuery.handleError(s, xml, null, e);
							}

						}, 100);

						xml = null;

					}
				};
				// Timeout checker
				if (s.timeout > 0) {
					setTimeout(function() {
						// Check to see if the request is still happening
						if (!requestDone)
							uploadCallback("timeout");
					}, s.timeout);
				}
				try {

					var form = jQuery('#' + formId);
					jQuery(form).attr('action', s.url);
					jQuery(form).attr('method', 'POST');
					jQuery(form).attr('target', frameId);
					if (form.encoding) {
						jQuery(form).attr('encoding', 'multipart/form-data');
					} else {
						jQuery(form).attr('enctype', 'multipart/form-data');
					}
					jQuery(form).submit();

				} catch (e) {
					jQuery.handleError(s, xml, null, e);
				}

				jQuery('#' + frameId).load(uploadCallback);
				return {
					abort : function() {
					}
				};

			},

			uploadHttpData : function(r, type) {
				var data = !type;
				data = type == "xml" || data ? r.responseXML : r.responseText;
				// If the type is "script", eval it in global context
				if (type == "script")
					jQuery.globalEval(data);
				// Get the JavaScript object, if JSON is used.
				if (type == "json")
					eval("data = " + data);
				// evaluate scripts within html
				if (type == "html")
					jQuery("<div>").html(data).evalScripts();

				return data;
			}
});
function signparam(param, timestamp, nonce) {
    var arr = [];
    for(key in param) {
        if (param[key] == undefined || param[key] == null || param[key] == '') {
            param[key] = '';
        }
        arr.push(key + '=' + param[key]);
    }
    arr.sort();
    
    var aparam = '';
    for(str in arr) {
        aparam += arr[str];
        aparam += '&';
    }
    aparam += timestamp;
    aparam += nonce;
    
    return hex_sha1(utf16to8(aparam));
}
