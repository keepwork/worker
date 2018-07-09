jQuery
		.extend({

			createUploadIframe : function(id, uri) {
				// create frame
				var _ifm=null;
				var frameId = 'jUploadFrame' + id;
				if (window.ActiveXObject) {
					if (jQuery.browser.version == "9.0"
							|| jQuery.browser.version == "10.0") {
						_ifm = document.createElement('iframe');
						_ifm.id = frameId;
						_ifm.name = frameId;
					} else if (jQuery.browser.version == "6.0"
							|| jQuery.browser.version == "7.0"
							|| jQuery.browser.version == "8.0") {
						 _ifm = document.createElement('<iframe id="'
								+ frameId + '" name="' + frameId + '" />');
						if (typeof uri == 'boolean') {
							_ifm.src = 'javascript:false';
						} else if (typeof uri == 'string') {
							_ifm.src = uri;
						}
					}
				}else {
					_ifm = document.createElement('iframe');
					_ifm.id = frameId;
					_ifm.name = frameId;
				}
				_ifm.style.position = 'absolute';
				_ifm.style.top='-1000px';
				_ifm.style.left='-1000px';
				document.body.appendChild(_ifm);
				return _ifm;
			},
			createUploadForm : function(id, fileElementId) {
				// create form
				var formId = 'jUploadForm' + id;
				var fileId = 'jUploadFile' + id;
				var form = jQuery('<form  action="" method="POST" name="'
						+ formId + '" id="' + formId
						+ '" enctype="multipart/form-data"></form>');
				var oldElement = jQuery('#' + fileElementId);
				var newElement = jQuery(oldElement).clone();
				jQuery(oldElement).attr('id', fileId);
				jQuery(oldElement).before(newElement);
				jQuery(oldElement).appendTo(form);
				// set attributes
				jQuery(form).css('position', 'absolute');
				jQuery(form).css('top', '-1200px');
				jQuery(form).css('left', '-1200px');
				jQuery(form).appendTo('body');
				return form;
			},
			ajaxFileUpload : function(s) {
				// them for all requests, not only timeout
				s = jQuery.extend({}, jQuery.ajaxSettings, s);
				var id = s.fileElementId;
				var form = jQuery.createUploadForm(id, s.fileElementId);
				var _ifm=jQuery.createUploadIframe(id, s.secureuri);
				var frameId = 'jUploadFrame' + id;
				var formId = 'jUploadForm' + id;
				if (s.global && !jQuery.active++) {
					// Watch for a new set of requests
					jQuery.event.trigger("ajaxStart");
				}
				var requestDone = false;
				// Create the request object
				var xml = {};
				if (s.global) {
					jQuery.event.trigger("ajaxSend", [ xml, s ]);
				}
				var uploadCallback = function(isTimeout) {
					// Wait for a response to come back
					var _ifm = document.getElementById(frameId);
					try {
						if (_ifm.contentWindow) {
							xml.responseText = _ifm.contentWindow.document.body ? _ifm.contentWindow.document.body.innerHTML
									: null;
							xml.responseXML = _ifm.contentWindow.document.XMLDocument ? _ifm.contentWindow.document.XMLDocument
									: _ifm.contentWindow.document;

						} else if (_ifm.contentDocument) {
							xml.responseText = _ifm.contentDocument.document.body ? _ifm.contentDocument.document.body.innerHTML
									: null;
							xml.responseXML = _ifm.contentDocument.document.XMLDocument ? _ifm.contentDocument.document.XMLDocument
									: _ifm.contentDocument.document;
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
							// Make sure that the request was successful or
							// notmodified
							if (status != "error") {
								// process the data (runs the xml through
								// httpData regardless of callback)
								var data = jQuery.uploadHttpData(xml,
										s.dataType);
								if (s.success) {
									// ifa local callback was specified, fire it
									// and pass it the data
									s.success(data, status);
								}
								;
								if (s.global) {
									// Fire the global callback
									jQuery.event.trigger("ajaxSuccess", [ xml,
											s ]);
								}
								;
							} else {
								jQuery.handleError(s, xml, status);
							}

						} catch (e) {
							status = "error";
							jQuery.handleError(s, xml, status, e);
						}
						;
						if (s.global) {
							// The request was completed
							jQuery.event.trigger("ajaxComplete", [ xml, s ]);
						};
						// Handle the global AJAX counter
						if (s.global && !--jQuery.active) {
							jQuery.event.trigger("ajaxStop");
						}
						;
						if (s.complete) {
							s.complete(xml, status);
						};
						jQuery(_ifm).unbind();
						setTimeout(function() {
							try {
								jQuery(_ifm).remove();
								jQuery(form).remove();

							} catch (e) {
								jQuery.handleError(s, xml, null, e);
							}

						}, 100);
						xml = null;
					};
				};
				// Timeout checker
				if (s.timeout > 0) {
					setTimeout(function() {
						if (!requestDone) {
							// Check to see ifthe request is still happening
							uploadCallback("timeout");
						}
					}, s.timeout);
				}
				try {
					var form = jQuery('#' + formId);
					jQuery(form).attr('action', s.url);
					jQuery(form).attr('method', 'POST');
					jQuery(form).attr('target', frameId);
					if (form.encoding) {
						form.encoding = 'multipart/form-data';
					} else {
						form.enctype = 'multipart/form-data';
					}
					jQuery(form).submit();

				} catch (e) {
					jQuery.handleError(s, xml, null, e);
				}
				if (window.attachEvent) {
					document.getElementById(frameId).attachEvent('onload',
							uploadCallback);
				} else {
					document.getElementById(frameId).addEventListener('load',
							uploadCallback, false);
				}
				return {
					abort : function() {
					}
				};
			},
		    handleError: function( s, xhr, status, e )      {  
			    // If a local callback was specified, fire it  
			            if ( s.error ) {  
			                s.error.call( s.context || s, xhr, status, e );  
			            }  
			      
			            // Fire the global callback  
			            if ( s.global ) {  
			                (s.context ? jQuery(s.context) : jQuery.event).trigger( "ajaxError", [xhr, s, e] );  
			            }  
			},  
			uploadHttpData : function(r, type) {
				var data = !type;
				data = type == "xml" || data ? r.responseXML : r.responseText;
				// ifthe type is "script", eval it in global context
				if (type == "script") {
					jQuery.globalEval(data);
				}
				// Get the JavaScript object, ifJSON is used.
				if (type == "json") {
					eval("data = " + data);
				}
				// evaluate scripts within html
				if (type == "html") {
					jQuery("<div>").html(data).evalScripts();
				}
				return data;
			}
		});
