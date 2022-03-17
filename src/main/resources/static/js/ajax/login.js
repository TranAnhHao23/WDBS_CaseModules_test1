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
            localStorage.setItem("accountId", data.id)
            window.open("newsfeed.html", "_self")
        },
        error: function (){
            window.location.href = "404-2.html"
        }
    })
}
function regis(){
    let fullName = $('#fullName').val();
    let phoneNumber = $('#phoneNumber').val();
    let email = $('#mail').val();
    let address = $('#address').val();
    let account = $('#acc').val();
    let password = $('#pass').val();
    let role = $('#role').val();
    let roles = [];
    roles.push(role)
    let signUp = {
        fullName : fullName,
        phoneNumber : phoneNumber,
        email : email,
        address : address,
        account : account,
        password : password,
        roles : roles
    };
    console.log(signUp)
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/auth/signup",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(signUp),
        success: function (data) {
            console.log("sign up success")
            console.log(data)
            window.open("landing.html", "_self")
        }
    })
}
