//february - 1
//april - 3
//june - 5
//september - 9
function change() {
    $("#bith_day option").remove();
    $("#bith_day").append($("<option></option>").text("day").val(""))
    month = document.getElementById('bith_month').value;

    if (month == "1") {
        for (var i = 1; i < 29; i++) {
            $("#bith_day").append(
                $("<option></option>").text(i).val(i)
            )
        }
    } else if (month == "3" || month == "5" || month == "8" ||
        month == "november") {
        for (var i = 1; i < 31; i++) {
            $("#bith_day").append(
                $("<option></option>").text(i).val(i)
            )
        }
    } else if (month != "") {
        for (var i = 1; i < 32; i++) {
            $("#bith_day").append(
                $("<option></option>").text(i).val(i)
            )
        }
    }
};