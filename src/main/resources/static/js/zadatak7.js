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
                alert("Nova zona uspesno dodata");
            }
        });
    });
});

function getData() {
    // Koriscenje .ajax funkcije da se povuku sve zone sa servera
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/Parking/zone",
        success: function (result) {
            $.each(result, function (i, item) {
                console.log("Cena " + item.naziv + " zone sa PDV-om iznosi: " + calculatePDV(item));

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

// Racunanje PDV-a cena po satu
function calculatePDV(item) {
    return item.cenaZaSat + item.cenaZaSat * 0.18;
}