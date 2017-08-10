function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
function redirectLogin() {
    var txt;
    var r = confirm("Please login to continue!!");
    if (r == true) {
        window.location="window.location.href='login.jsp'";
    } else {
        txt = "You pressed Cancel!";
    }
    document.getElementById("demo").innerHTML = txt;
}
