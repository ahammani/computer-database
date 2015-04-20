
$( "#addComputer" ).validate({
  rules: {
    field: {
      required: true,
      dateISO: true
    }
  }
});