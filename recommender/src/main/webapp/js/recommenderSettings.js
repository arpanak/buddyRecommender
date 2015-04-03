$( document ).ready(
		function () {
			$( "#fileuploader" ).uploadFile(
					{
						url : "uploader.do",
						fileName : "fileName",
						allowedTypes : "csv",
						onSuccess : function ( files, data, xhr ) {
							$( "#eventsmessage" ).html( "" );
							$( "#eventsmessage" ).html( $( "#eventsmessage" ).html() + "<br/>Successfully imported data." );

						},
						onError : function ( files, status, errMsg ) {
							$( "#eventsmessage" ).html( "" );
							$( "#eventsmessage" ).html(
									$( "#eventsmessage" ).html()
											+ "<br/>Error occurred while uploading the following files: "
											+ JSON.stringify( files ) );
						}
					} );

			function switchDiv ( divId ) {
				$( "#content span" ).each( function ( index, item ) {
					if ( $( item ).prop( "id" ) === divId ) {
						$( item ).show();
						$( "li[name='" + divId + "']" ).addClass( "current" );
					} else {
						$( item ).hide();
						$( "li[name='" + $( item ).prop( "id" ) + "']" ).removeClass( "current" );
					}
				} );
			}

			switchDiv( "importEmployeeDetails" );
			$( "li[name='importEmployeeDetails']" ).click( function () {
				switchDiv( "importEmployeeDetails" )
			} );

			$( "li[name='configureAssigneeTemplate']" ).click( function () {
				switchDiv( "configureAssigneeTemplate" );
				$.ajax( {
					type : "POST",
					url : "emailTemplate.do",
					data : {
						formType : "getAssigneeTemplate"
					},
					success : function ( email ) {
						$( "#cc" ).val( email.cc );
						$( "#subject" ).val( email.subject );
						$( "#mailContent" ).val( email.emailContent );
					}
				} );
			} );

			$( "#sendMail" ).validate( {
				rules : {
					cc : {
						email : true
					},
					subject : {
						required : true
					},
					mailContent : {
						required : true
					}
				},
				messages : {
					subject : {
						required : "Subject required"
					},
					mailContent : {
						required : "Please enter mail content"
					}
				}
			} );

			function submitForm ( url ) {
				if ( $( "#sendMail" ).valid() ) {
					$( "#submit" ).val( "Saving template.." );
					$.ajax( {
						type : "POST",
						url : url,
						data : $( "#sendMail" ).serialize(),
						success : function ( data ) {
							$( "#templateResponse" ).html( data );
							$( "#submit" ).val( "Save template" );
						}
					} );
				}
			}

			$( "#sendMail" ).submit( function () {
				var url = "emailTemplate.do";
				submitForm( url );
				return false;
			} );

		} );