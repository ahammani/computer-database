
$( "#myform" ).validate({
  rules: {
    field: {
      required: true,
      dateISO: true
    }
  }
});