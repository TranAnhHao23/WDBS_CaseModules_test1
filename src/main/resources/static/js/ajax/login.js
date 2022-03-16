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
        success: function (data) {
            console.log("hello anh em")
            console.log(data)
            window.open("newsfeed.html", "_blank")
        },
        error: function (){
            window.location.href = "404-2.html"
        }
    })
}