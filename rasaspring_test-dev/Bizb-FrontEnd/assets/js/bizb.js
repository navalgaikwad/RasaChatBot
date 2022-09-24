//document.getElementById("wisdom").focus();

		// Initialize the Amazon Cognito credentials provider
		/* AWS.config.region = 'us-east-1'; // Region
		AWS.config.credentials = new AWS.CognitoIdentityCredentials({
			// Provide your Pool Id here
			IdentityPoolId : 'us-east-1:abb3e186-ef3a-4397-9468-1033b612ff1f',
		});

		var lexruntime = new AWS.LexRuntime();
		var lexUserId = 'chatbot-vbhv' + Date.now();*/
		var sessionAttributes = 'rasa_' + Date.now();
		var flag = '';

		function pushChat() {

			// if there is text to be sent...
			var wisdomText = document.getElementById('wisdom');
			if (wisdomText && wisdomText.value
					&& wisdomText.value.trim().length > 0) {

				// disable input to show we're sending it
				var wisdom = wisdomText.value.trim();
				wisdomText.value = "...";
				//wisdomText.locked = true;

				// send it to the Lex runtime
				var params = {
					message : wisdom,
					sender : sessionAttributes,
					flag : flag
				};
				flag='';
				showRequest(wisdom);

				$.ajax({
							url : "http://localhost:9090/hit",
							method : "POST",
							data : JSON.stringify(params),
							contentType : "application/json",
							success : function(data) {
								//console.log(data);
								var arrayLength = data.length;
								if (arrayLength > 0) {
									// capture the sessionAttributes for the next cycle
									sessionAttributes = data[0].recipient_id;
									flag = data[0].flag;
									var divText = '<div id="options" class="options">';
									for (var i = 0; i < arrayLength; i++) {
                                        var inText=false;
										var str = '';
										var url = '';

										// show response and/or error/dialog status
										if (data[i].image != null) {
											//console.log(data[i].image);//.replaceAll("\\/", "\\")
											url = "\n"
													+ data[i].image.replaceAll(
															"\\/", "\\");
											//showImgInResponse(url);
											divText += '<img src="'
													+ url
													+ '" style="width: 200px; height: 200px;"';
										}

										if (data[i].text != null) {
											str += "\n" + data[i].text;
											//divText+='<P >'+data[i].text+'</P>';
											showResponse(str);
										}

										if (data[i].buttons != null) {

											var bLen = data[i].buttons.length;

											for (var j = 0; j < bLen; j++) {
												//console.log(data[i].buttons[j].title);
												//createButton(data[i].buttons[j].title);
												//divText+='<div class="option" onClick=\"pushChat2('+data[i].buttons[j].title+');\">'+data[i].buttons[j].title+'</div>';
												divText += '<a onclick="submitOption(this)" data-option="'
														+ data[i].buttons[j].title
														+ '" class="option" >'
														+ data[i].buttons[j].title
														+ '</a>';

											}

										}

										/* if(url!=''){
											//showResponse(str);
											showImgInResponse(url);
										}else{
											showResponse(str);
										} */
									}

									if (!inText) {
										divText += '</div>';

									}
									createDiv(divText.trim());
									console.log(divText);
								}
							}
						});
				// re-enable input
				wisdomText.value = '';
				document.getElementById('wisdom').disabled=true;
				//wisdomText.locked = false;

				/* lexruntime.postText(params, function(err, data) {
					if (err) {
						console.log(err, err.stack);
						showError('Error:  ' + err.message
								+ ' (see console for details)')
					}
					if (data) {
						// capture the sessionAttributes for the next cycle
						sessionAttributes = data.sessionAttributes;
						// show response and/or error/dialog status
						showResponse(data);
					}
					// re-enable input
					wisdomText.value = '';
					wisdomText.locked = false;
				}); */
			}
			// we always cancel form submission
			return false;
		}

		function pushChat2(wisdom) {

			// if there is text to be sent...

			if (wisdom && wisdom.trim().length > 0) {

				// disable input to show we're sending it
				//var wisdom = wisdomText.value.trim();
				//wisdomText.value = "...";
				//wisdomText.locked = true;

				// send it to the Lex runtime
				var params = {
					message : wisdom,
					sender : sessionAttributes,
					flag : flag
				};
				flag='';
				showRequest(wisdom);

				$.ajax({
							url : "http://localhost:9090/hit",
							method : "POST",
							data : JSON.stringify(params),
							contentType : "application/json",
							success : function(data) {
								//console.log(data);
								var arrayLength = data.length;
								if (arrayLength > 0) {
									// capture the sessionAttributes for the next cycle
									sessionAttributes = data[0].recipient_id;
									flag = data[0].flag;
									var divText = '<div id="options class="options">';
									var inText = false;
									for (var i = 0; i < arrayLength; i++) {
										var str = '';
										var url = '';

										// show response and/or error/dialog status
										if (data[i].image != null) {
											//console.log(data[i].image);//.replaceAll("\\/", "\\")
											url = "\n"
													+ data[i].image.replaceAll(
															"\\/", "\\");
											divText += '<img src="'
													+ url
													+ '" style="width: 200px; height: 200px;"';
										}

										if (data[i].text != null) {
											str += "\n" + data[i].text;
											//divText+='<P>'+data[i].text+'</P>';
											inText = true;
											divText = '';
											if(data[i].input=='Enable')
											{
												enableInput();
											}
											showResponse(str);
										}

										if (data[i].buttons != null) {

											var bLen = data[i].buttons.length;

											for (var j = 0; j < bLen; j++) {
												//console.log(data[i].buttons[j].title);
												//createButton(data[i].buttons[j].title);
												//divText+='<div class="option" onClick=\"pushChat2('+data[i].buttons[j].title+');\">'+data[i].buttons[j].title+'</div>';
												divText += '<a onclick="submitOption(this)" data-option="'
														+ data[i].buttons[j].title
														+ '" class="option" >'
														+ data[i].buttons[j].title
														+ '</a>';

											}

										}

										/* if(url!=''){
											//showResponse(str);
											showImgInResponse(url);
										}else{
											showResponse(str);
										} */
									}
									if (!inText) {
										divText += '</div>';

									}
									createDiv(divText.trim());
									console.log(divText);

								}
							}
						});
				// re-enable input
				//wisdomText.value = '';
				//wisdomText.locked = false;

				/* lexruntime.postText(params, function(err, data) {
					if (err) {
						console.log(err, err.stack);
						showError('Error:  ' + err.message
								+ ' (see console for details)')
					}
					if (data) {
						// capture the sessionAttributes for the next cycle
						sessionAttributes = data.sessionAttributes;
						// show response and/or error/dialog status
						showResponse(data);
					}
					// re-enable input
					wisdomText.value = '';
					wisdomText.locked = false;
				}); */
			}
			// we always cancel form submission
			return false;
		}

		function getInitMenu() {
			pushChat2("menu");
			/* $.ajax({
			   url: "http://localhost:9090/init-menu/"+sessionAttributes,
			   method: "GET",
			   //data: JSON.stringify(params),
			   contentType: "application/json",
			   success: function (data) {
			       console.log(data);
			       var arrayLength=data.length;
			       if (arrayLength>0) {
			       	// capture the sessionAttributes for the next cycle
					//sessionAttributes = data[0].recipient_id;

					for (var i = 0; i < arrayLength; i++) {
						var str = '';
						var url = '';
						// show response and/or error/dialog status
						if(data[i]!=null)
							str+="\n"+data[i];

						if(str!=''){
							createButton(str);
						}
					}
				}
			   }
			});  */
		}

		function submitOption(el) {
			var option = el.dataset.option;
			//alert(option);
			//$("#options").remove();
			$("p").remove(".options");
			pushChat2(option)
		}

		function createDiv(divText) {
			if (divText != ""
					|| divText != '<div id="options" class="options"></div>') {
				var conversationDiv = document.getElementById('conversation');
				var responsePara = document.createElement("P");
				responsePara.className = 'options';
				var div = document.createElement("div");
				div.innerHTML = divText;
				responsePara.appendChild(div);
				conversationDiv.appendChild(responsePara);
				conversationDiv.scrollTop = conversationDiv.scrollHeight;
			}
		}

		function createButton(str) {
			var conversationDiv = document.getElementById('conversation');
			var responsePara = document.createElement("P");
			responsePara.className = 'lexResponse';
			var btn = document.createElement("BUTTON");
			btn.innerHTML = str;
			btn.onclick = function() {
				pushChat2(str)
			};
			responsePara.appendChild(btn);
			//str.link(pushChat2(str));
			//responsePara.appendChild(document.createTextNode(str));
			conversationDiv.appendChild(responsePara);
			conversationDiv.scrollTop = conversationDiv.scrollHeight;
		}

		function showRequest(daText) {

			var conversationDiv = document.getElementById('conversation');
			var requestPara = document.createElement("P");
			requestPara.className = 'messages__item messages__item--visitor';
			requestPara.appendChild(document.createTextNode(daText));
			conversationDiv.appendChild(requestPara);
			conversationDiv.scrollTop = conversationDiv.scrollHeight;
            /*var typing = document.createElement("P");
            typing.id='typing';
            typing.className = 'messages__item messages__item--operator';
            typing.innerHTML = 'typing...';*/
	    var typing = document.createElement('div');
			typing.id='typing';
			typing.innerHTML = '<div class="dot"></div><div class="dot"></div><div class="dot"></div>';
			typing.className = 'messages__item messages__item--operator';
            conversationDiv.appendChild(typing);
			conversationDiv.scrollTop = conversationDiv.scrollHeight;
        }

		function showError(daText) {

			var conversationDiv = document.getElementById('conversation');
			var errorPara = document.createElement("P");
			errorPara.className = 'lexError';
			errorPara.appendChild(document.createTextNode(daText));
			conversationDiv.appendChild(errorPara);
			conversationDiv.scrollTop = conversationDiv.scrollHeight;
		}

		function showImgInResponse(url) {
			var conversationDiv = document.getElementById('conversation');
			var responsePara = document.createElement("P");
			responsePara.className = 'options';
			var img = document.createElement('img');
			img.src = url;
			img.style.width = '200px';
			img.style.height = '200px';
			responsePara.appendChild(img);
			conversationDiv.appendChild(responsePara);
			conversationDiv.scrollTop = conversationDiv.scrollHeight;
		}

		function showResponse(lexResponse) {

			var conversationDiv = document.getElementById('conversation');
            var typing=document.getElementById('typing');
			var responsePara = document.createElement("P");
			responsePara.className = 'messages__item messages__item--operator';
			if (lexResponse) {
				responsePara.appendChild(document.createTextNode(lexResponse));
				responsePara.appendChild(document.createElement('br'));
			}
            conversationDiv.removeChild(typing);
			conversationDiv.appendChild(responsePara);
			conversationDiv.scrollTop = conversationDiv.scrollHeight;
		}

		function onChatCheck() {
			var decider = document.getElementById('check');
				clearBox('wisdom');
				clearBox('conversation');
				sessionAttributes = 'Rasa_' + Date.now();
				getInitMenu();
		}
		function clearBox(elementID) {
			document.getElementById(elementID).innerHTML = "";
		}
		function enableInput()
		{
			document.getElementById('wisdom').disabled = false
		}