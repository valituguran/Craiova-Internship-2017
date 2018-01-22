
function sortTableAscending() {
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("currencyTable");
    switching = true;

    while (switching) {

        switching = false;
        rows = table.getElementsByTagName("TR");

        for (i = 1; i < (rows.length - 1); i++) {

            shouldSwitch = false;

            x = rows[i].getElementsByTagName("TH")[0];
            y = rows[i + 1].getElementsByTagName("TH")[0];

            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {

                shouldSwitch= true;
                break;
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
             and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}
function sortTableDescending() {
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("currencyTable");
    switching = true;

    while (switching) {

        switching = false;
        rows = table.getElementsByTagName("TR");

        for (i = 1; i < (rows.length - 1); i++) {

            shouldSwitch = false;

            x = rows[i].getElementsByTagName("TH")[0];
            y = rows[i + 1].getElementsByTagName("TH")[0];

            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {

                shouldSwitch= true;
                break;
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
             and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}