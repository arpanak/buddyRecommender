function initializeDialogContent() {
	$.ajax({
		type : "GET",
		url : "SendMail.jsp",
		success : function(msg) {
			$("#dialog").html(msg);
		}
	});
}

function setupAutocomplete(targetElement, propertyName) {
	targetElement.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : "GET",
				url : "suggester.do",
				data : {
					'property' : propertyName,
					'value' : request.term
				},
				dataType : "text",
				success : function(msg) {
					response(eval(msg));
				}
			});
		}

	});

}

function initializeAssigneeTrackerTable(containerElementId) {

	$("#" + containerElementId).jtable(
			{
				title : 'Assigned buddies',
				actions : {
					listAction : '/recommender/buddy.do'
				},
				ajaxSettings : {
					contentType : 'application/json'
				},
				fields : {
					buddyId : {
						key : true,
						list : false
					},
					buddyName : {
						title : 'Buddy Name',
						width : '40%'
					},
					assigneeName : {
						title : 'Joinee Name',
						width : '20%'
					}
				},
				recordsLoaded : function(event, data) {
					$("#" + containerElementId + " table tr:even").css(
							"background-color", "#f3f3f3");
				}
			});

	$("#" + containerElementId).jtable("load");

}

function initializeRecommendedResultsTable(containerElementId) {

	$("#" + containerElementId).jtable(
			{
				title : '<h3>Recommended buddies: </h3>',
				selecting : true,
				selectOnRowClick : true,
				multiselect : false,
				actions : {
					listAction : function(postData, jtParams) {
						return $.Deferred(function($dfd) {
							$.ajax({
								type : "POST",
								url : "recommender.do",
								dataType : 'json',
								data : $("#joineeDetailsForm").serialize(),
								success : function(data) {
									$dfd.resolve(data);
								},
								error : function() {
									$dfd.reject();
								}
							});

						});
					}
				},
				ajaxSettings : {
					contentType : 'application/json'
				},
				fields : {
					id : {
						key : true,
						list : false
					},
					employeeCode : {
						title : 'Employee code',
						width : '10%'
					},
					name : {
						title : 'Buddy Name',
						width : '35%'
					},
					currentTeam : {
						title : 'Team',
						width : '35%'
					},
					careerLevel : {
						title : 'Career Level',
						width : '10%'
					},
					RowCheckbox : {
						title : 'Select Buddy',
						width : '10%',
						type : 'checkbox',
						values : {
							'false' : 'Passive',
							'true' : 'Active'
						},
						defaultValue : 'false',
						display : function(data) {
							return "<a href='#'>Assign</a>";
						}
					}
				},
				recordsLoaded : function(event, data) {
					$("#" + containerElementId + " table tr:even").css(
							"background-color", "#f3f3f3");
				},
				// Register to selectionChanged event to handle events
				selectionChanged : function() {
					var $selectedRows = $('#' + containerElementId).jtable(
							'selectedRows');
					var employeeId = $('.jtable-row-selected').attr(
							"data-record-key");
					$selectedRows.each(function() {
						$.ajax({
							type : "POST",
							url : "emailTemplate.do",
							data : {
								joineeName : $("#name").val(),
								employeeId : employeeId
							},
							success : function(email) {
								$("#selectedEmployeeId").val(employeeId);
								$('.jtable-row-selected').removeClass(
										'jtable-row-selected');
								$("#to").val(email.toAddress);
								$("#cc").val(email.cc);
								$("#subject").val(email.subject);
								$("#mailContent").val(email.emailContent);
								$('#dialog').dialog('open');
							}
						});
					});
				}
			});

	$("#" + containerElementId).jtable("load");

}

$(document).ready(
		function() {
			function switchDiv(divId) {
				$("#content span").each(
						function(index, item) {
							if ($(item).prop("id") === divId) {
								$(item).show();
								$("li[name='" + divId + "']").addClass(
										"current");
							} else {
								$(item).hide();
								$("li[name='" + $(item).prop("id") + "']")
										.removeClass("current");
							}
						});
			}

			switchDiv("recommender");
			$("li[name='recommender']").click(function() {
				switchDiv("recommender");
			});
			$("li[name='trackBuddies']").click(function() {
				switchDiv("trackBuddies");
				if ($('#assignedBuddiesContainer table.jtable').length != 0) {
					$('#assignedBuddiesContainer').jtable("destroy");
				}
				initializeAssigneeTrackerTable('assignedBuddiesContainer');
			});

			$("#dialog").dialog({
				autoOpen : false,
				open : initializeDialogContent(),
				minWidth : 500
			});

			$("#suggestBuddies").click(function() {
				initializeDialogContent();
			});

			$("#joineeDetailsForm").validate({
				rules : {
					name : {
						required : true
					},
					college : {
						required : true
					},
					passoutyear : {
						required : true,
						number : true
					},
					team : {
						required : true
					}
				},
				messages : {
					name : {
						required : "Please enter joinee's name"
					},
					college : {
						required : "Please enter joinee's college"
					},
					passoutyear : {
						required : "Please enter joinee's year of graduation"
					},
					team : {
						required : "Please enter joinee's team"
					}
				}
			});

			setupAutocomplete($("#team"), "currentTeam");
			setupAutocomplete($("#college"), "graduateInstitute");

			$("#joineeDetailsForm").submit(function() {
				var url = "recommender.do";
				if ($("#joineeDetailsForm").valid()) {
					if ($('#responseContent table.jtable').length != 0) {
						$('#responseContent').jtable("destroy");
					}
					initializeRecommendedResultsTable("responseContent");
				}

				return false;
			});

		});