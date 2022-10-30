function toggle(event) {
  var pa = event.parentNode;
  pa.classList.toggle("on");
}

var birth_date = ["", "", ""];
var birth;

function selectDate(event) {
  var va = event.value;
  // console.log(va);

  birth += va;
  console.log(birth);
  $("input[name=pgBirth]").attr("value", "1999-05-17");
}

function selectYear(event) {
  let year = event.value;
  birth_date[0] = year;
  birth = birth_date[0] + birth_date[1] + birth_date[2];

  $('input[name="pgBirth[]"]')[0].setAttribute("value", birth);
  console.log(
    "jqueryYear: " + $('input[name="pgBirth[]"]')[0].getAttribute("value")
  );
}

function selectMonth(event) {
  let month = event.value;
  birth_date[1] = month;
  birth = birth_date[0] + birth_date[1] + birth_date[2];
  $('input[name="pgBirth[]"]')[0].setAttribute("value", birth);
  console.log(
    "jqueryMonth: " + $('input[name="pgBirth[]"]')[0].getAttribute("value")
  );
}

function selectDay(event) {
  let day = event.value;
  birth_date[2] = day;
  birth = birth_date[0] + birth_date[1] + birth_date[2];
  $('input[name="pgBirth[]"]')[0].setAttribute("value", birth);
  console.log(
    "jqueryDay: " + $('input[name="pgBirth[]"]')[0].getAttribute("value")
  );
}
