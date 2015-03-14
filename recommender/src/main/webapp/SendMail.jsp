<script type="text/javascript">
	$(document).ready(function() {

		$("#sendMail").validate({
			rules : {
				to : {
					required : true,
					email: true
				},
				cc : {
					required : true,
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
					required : "Please enter To email address"
				},
				cc : {
					required : "Please enter CC email address"
				},
				subject : {
					required : "Please enter subject"
				},
				mailContent : {
					required : "Please enter mail content"
				}
			}
		});

	})
</script>

<form name="sendMail" id="sendMail" action="email.do" method="post">
	<table>
		<tr>
			<td><label>To:</label></td>
			<td><input type="text" name="to" /></td>
		</tr>
		<tr>
			<td><label>CC:</label></td>
			<td><input type="text" name="cc" /></td>
		</tr>
		<tr>
			<td><label>Subject:</label></td>
			<td><input type="text" name="subject" /></td>
		</tr>
	</table>
	<p>
		<textarea name="mailContent" rows=8 cols=50></textarea>
	</p>
	<p>
		<input type="submit" name="submit" value="Send Mail" />
	</p>
</form>