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
</style>
<script type="text/javascript">
	$(document).ready(function() {

		$("#sendMail").validate({
			rules : {
				to : {
					required : true,
					email: true
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
					email: "Enter valid address"
				},
				subject : {
					required : "Subject required"
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