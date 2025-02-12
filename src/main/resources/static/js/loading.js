document.addEventListener("DOMContentLoaded", function () {
    const loadingBar = document.createElement("div");
    loadingBar.id = "loading-bar";
    document.body.appendChild(loadingBar);
});

window.addEventListener("beforeunload", function () {
    document.getElementById("loading-bar").style.width = "100%";
});

document.addEventListener("load", function () {
    document.getElementById("loading-bar").style.width = "0%";
});
