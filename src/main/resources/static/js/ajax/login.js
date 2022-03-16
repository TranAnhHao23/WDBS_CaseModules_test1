function login() {
    let username = $('#username').val();
    let password = $('#password').val();
    let userInfo = {
        username: username,
        password: password
    };
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/auth/login",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(userInfo),
        success: function () {
            console.log("hello anh em")
            window.location.href = "newsfeed.html"
        },
        error: function (){
            window.open("about.html");
        }
    })
}