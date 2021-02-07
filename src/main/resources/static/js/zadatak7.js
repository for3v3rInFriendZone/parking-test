$(document).ready(function () {
    getData();

    $("#zoneForm").submit(function (event) {
        // Stop form from submitting normally
        event.preventDefault();

        // Get some values from elements on the page:
        var naziv = $("#naziv").val();
        var cenaZaSat = $("#cenaZaSat").val();
        var dozvoljenoVremeParkingaUSatima = $("#dozvoljenoVremeParkingaUSatima").val();

        var url = "http://localhost:8080/Parking/zone";
        var requestBody = JSON.stringify({naziv, cenaZaSat, dozvoljenoVremeParkingaUSatima});

        // Send the data using post
        $.ajax({
            url: url,
            type: "POST",
            data: requestBody,
            contentType: "application/json",
            dataType: "json",
            success: function () {

            }
        });
    });
});

function getData() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/Parking/zone",
        success: function (result) {
            $.each(result, function (i, item) {
                var $tr = $('<tr>').append(
                    $('<td>').text(item.naziv),
                    $('<td>').text(item.cenaZaSat),
                    $('<td>').text(item.cenaZaSat + item.cenaZaSat * 0.18),
                    $('<td>').text(item.dozvoljenoVremeParkingaUSatima)
                );

                $("#podaci").append($tr);
            });
        },
        error: function () {
            alert("Neka greska se desila prilikom preuzimanja Zona.");
        }
    });
}