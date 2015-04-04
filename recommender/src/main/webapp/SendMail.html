<style>
#sendMail label.error {
	color: red;
}

#sendMail input.error {
	border: 1px solid red;
}

#sendMail textarea.error {
	border: 1px solid red;
}

#to, #cc, #subject {
	width: 400px;
}
</style>
<script type="text/javascript">
	$( document ).ready( function () {

		$( "#sendMail" ).validate( {
			rules : {
				to : {
					required : true,
					email : true
				},
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
				to : {
					required : "To address required",
					email : "Enter valid address"
				},
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
				$( "#submit" ).val( "Sending mail" );
				$( "#submit" ).attr( "disabled", "true" );
				$.ajax( {
					type : "POST",
					url : url,
					data : $( "#sendMail" ).serialize(),
					success : function ( data ) {
						$( "#sendMail" ).parent().html( data );
					}
				} );
			}
		}

		$( "#sendMail" ).submit( function () {
			var url = "assignment.do";
			submitForm( url );
			return false;
		} );

	} );
</script>

<form name="sendMail" id="sendMail" action="email.do" method="post">
	<table>
		<tr>
			<td><label>To:</label></td>
			<td><input type="text" name="to" id="to" /></td>
		</tr>
		<tr>
			<td><label>CC:</label></td>
			<td><input type="text" name="cc" id="cc" /></td>
		</tr>
		<tr>
			<td><label>Subject:</label></td>
			<td><input type="text" name="subject" id="subject" /></td>
		</tr>
	</table>
	<p>
		<textarea name="mailContent" id="mailContent" rows=8 cols=50></textarea>
	</p>
	<p>
		<input type="submit" id="submit" name="submit" value="Send Mail" /> <input
			type="hidden" id="selectedEmployeeId" name="selectedEmployeeId" />
	</p>
</form>