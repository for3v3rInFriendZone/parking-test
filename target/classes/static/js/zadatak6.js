$(document).ready(function () {
    $("#kartaForm").submit(function (event) {
        event.preventDefault();

        if (!$("#registracija").val()) {
            alert("Registracija je obavezno polje!");
            return;
        }

        if (!$("#pocetakParkinga").val()) {
            alert("Pocetak parkinga je obavezno polje!");
            return;
        }

        var trajanje = $("#trajanjeUMinutima").val();
        if (!trajanje) {
            alert("Trajanje u minutima je obavezno polje!");
            return;
        }

        if (!$.isNumeric(trajanje)) {
            alert("Trajanje u minutima mora biti ceo broj!");
            return;
        }

        if (trajanje <= 15) {
            alert("Trajanje u minutima mora biti vece od 15 minuta!");
            return;
        }

        if ($("#zona").val() === "crvena" && trajanje > 120) {
            alert("Trajanje u minutima u crvenoj zoni mora biti manje od 120 minuta!");
            return;
        }

        alert("Uspesno popunjena forma");
    });
});