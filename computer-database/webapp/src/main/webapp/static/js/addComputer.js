$("#addComputerForm").validate({
	rules : {
		computerName : {
			required : true
		},
		introduced : {
			dateISO : true
		},
		discontinued : {
			dateISO : true
		}
	},
	messages : {
		introduced : "Please enter a valid date. (YYYY-MM-DD)",
		disontinued : "Please enter a valid date. (YYYY-MM-DD)"
	}
});