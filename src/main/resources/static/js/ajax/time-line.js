function addUser() {
    let idUser = localStorage.getItem("accountFriend");

    $.ajax({
        type: "POST",
        url: `http://localhost:8080/users/find/${idUser}`,
        success: function (user) {
            document.getElementById("imageNavbar").innerHTML = `<img src="${user.imgUrl}" style="width: 32px; height: 32px;border-radius: 100%" alt="">`
            document.getElementById("imageMain").innerHTML = `<img src="${user.imgUrl}" alt="">
                                <form class="edit-phto">
                                    <i class="fa fa-camera-retro"></i>
                                    <label class="fileContainer">
                                        Edit Display Photo
                                        <input type="file"/>
                                    </label>
                                </form>`
            document.getElementById("nameUser").innerHTML = `<h5>${user.fullName}</h5>`
            // document.getElementById("imagePost").innerHTML = `<img src="${user.imgUrl}" alt="">`
        }
    })
}

function getFriendList() {
    let idUser = localStorage.getItem("accountFriend");
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            // 'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: `http://localhost:8080/users/${idUser}/friend-list`,

        success: function (users) {
            let content = "";
            for (let i = 0; i < users.length; i++) {
                content += `<li>
                    <figure>
                        <img src="${users[i].imgUrl}" alt="">
                            <span class="status f-online"></span>
                    </figure>
                    <div class="friendz-meta">
                        <a href="../time-line.html">${users[i].fullName}</a>
                        <i><a href="https://wpkixx.com/cdn-cgi/l/email-protection" class="__cf_email__"
                              data-cfemail="f2859b9c869780819d9e969780b2959f939b9edc919d9f">[email&#160;protected]</a></i>
                    </div>
                </li>`
            }
            document.getElementById("people-list").innerHTML = content;
        }
    })
}

function getNonFriend() {
    let idUser = localStorage.getItem("accountFriend");
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            // 'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: `http://localhost:8080/users/${idUser}/non-friend-list`,

        success: function (users) {
            let content = "";
            for (let i = 0; i < users.length; i++) {
                content += `<li>
                    <figure><img src="${users[i].imgUrl}" alt=""></figure>
                    <div class="friend-meta">
                        <h4><a href="time-line.html" onclick="getTimeLine(${idUser},${users[i].idUser})" title="">${users[i].fullName}</a></h4>
                        <a href="#" title="" class="underline">Add Friend</a>
                    </div>
                </li>`
            }
            document.getElementById("non-friend-list").innerHTML = content;
        }
    })
}

function postTimeline(){
    let idUserFrom = localStorage.getItem("accountId");
    let idUserTo = localStorage.getItem("accountFriend");
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            // 'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        url: `http://localhost:8080/post/${idUserFrom}/${idUserTo}/timeline`,

        success: function (posts) {
            let content = "";
            for (let i = 0; i < posts.length; i++) {
                content += postDetail(posts[i]);
            }
            document.getElementById('postInTimeline').innerHTML = content;
        }
    })
    event.preventDefault()
}

function postDetail(post) {
    let idUser = localStorage.getItem("accountId");

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/like-post/${post.idPost}/${idUser}/showLike`,

        success: function (like) {
            document.getElementById(`likePost(${post.idPost})`).innerText = like
        }
    })

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/comment/${post.idPost}/list-comment`,

        success: function (comments) {
            let content = ""
            if (comments != null) {
                for (let i = 0; i < comments.length; i++) {
                    content += `<li ><div class="comet-avatar"><img src="${comments[i].user.imgUrl}" style="width: 45px; height: 45px; border-radius: 100%" alt="">
                                </div>
                                <div class="we-comment">
                                <div class="coment-head">
                                    <h5><a href="../time-line.html" title="">${comments[i].user.fullName}</a></h5>
                                    <a class="we-reply" href="#" title="Reply"><i class="fa fa-reply"></i></a>
                                </div>
                                <p>${comments[i].content}</p>
                                </div>`
                    if (comments[i].comment != null) {
                        content += `<ul>
                                <li>
                                    <div class="comet-avatar">
                                    <img src="${comments[i].comment.user.imgUrl}" style="width: 45px; height: 45px; border-radius: 100%" alt="">
                                    </div>
                                    <div class="we-comment">
                                        <div class="coment-head">
                                            <h5><a href="../time-line.html" title="">${comments[i].comment.user.fullName}</a></h5>
                                        </div>
                                        <p>${comments[i].comment.content}</p>
                                    </div>
                                </li>
                                </ul>`
                    }
                    content += `</li>`

                }
                content += `<li>
                        <a href="#" title="" class="showmore underline">more comments</a>
                    </li>
                    <li class="post-comment">
                        <div class="comet-avatar">
                            <img src="../static/images/resources/comet-1.jpg" alt="">
                        </div>
                        <div class="post-comt-box">
                            <form method="post">
                                <textarea placeholder="Post your comment"></textarea>
                                <div class="add-smiles">
                                    <a href="#"><i class="fa fa-paper-plane" style=" color: blue" title="SEND"></i></a>
                                </div>
                            </form>
                        </div>
                    </li>`
                console.log(content);
                document.getElementById(`commentPost(${post.idPost})`).innerHTML = content;
            }
        }
    })

    return `<div class = "central-meta item" >
            <div class = "user-post">
            <div class = "friend-info" >
            <figure><img src="${post.userPost.imgUrl}" style="width: 40px; height: 40px; border-radius: 100%" alt=""></figure>
            <div class="friend-name">
            <ins><a href="time-line.html" onclick="getTimeLine(${idUser},${post.userPost.idUser})" title="${post.userPost.fullName}">${post.userPost.fullName}</a></ins></div>
            <div class="post-meta">
                <img src="${post.imgUrl}" alt="">
                    <div class="description">
                        <p>${post.content}</p>
                    </div>
                    <div class="we-video-info">
                        <ul>
                            <li>
                            <a href="#" onclick="likePost(${post.idPost})">
                                <span class="like" data-toggle="tooltip" title="like">
                                    <i class="ti-heart"></i>
                                    <ins id="likePost(${post.idPost})"></ins>
                                </span></a>
                            </li>
                        </ul>
                    </div>
            </div>
            </div>
    <div class="coment-area">
        <ul class="we-comet" id="commentPost(${post.idPost})">
            
        </ul>
    </div>
</div>
</div>`
}

function likePost(idPost) {
    let idUser = localStorage.getItem("accountId");

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/like-post/${idPost}/${idUser}/checkLike`,

        success: function (like) {
            document.getElementById(`likePost(${idPost})`).innerText = like
        }
    })
    event.preventDefault();
}

function checkFriendStatus(){
    let idUserFrom = localStorage.getItem("accountId");
    let idUserTo = localStorage.getItem("accountFriend");

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/friend-list/${idUserFrom}/${idUserTo}/checkFriendShip`,

        success: function (status){
            if (idUserFrom === idUserTo){
                document.getElementById("friendShip").hidden = true;
            } else {
                if (status === "friend"){
                    document.getElementById("friendShip").hidden = false;
                    document.getElementById("friendShip").innerHTML = `<a href="#" title="" data-ripple="">UnFriend</a>
                                                                                <a href="#" title="" data-ripple="">Block</a>`
                } else if (status ==="pending"){
                    document.getElementById("friendShip").hidden = false;
                    document.getElementById("friendShip").innerHTML = `<a href="#" title="" data-ripple="">Cancel Request</a>
                                                                                <a href="#" title="" data-ripple="">Block</a>`
                } else if (status === "respond"){
                    document.getElementById("friendShip").hidden = false;
                    document.getElementById("friendShip").innerHTML = `<a href="#" title="" data-ripple="">Accept Request</a>
                                                                                <a href="#" title="" data-ripple="">Cancel Request</a>
                                                                                <a href="#" title="" data-ripple="">Block</a>`
                } else if (status === "block"){
                    document.getElementById("friendShip").hidden = false;
                    document.getElementById("friendShip").innerHTML = `<a href="#" title="" data-ripple="">Unblock</a>`
                } else if (status === "blocked"){
                    document.getElementById("friendShip").hidden = true;
                }
            }

        }
    })
}

window.onload = function (){
    addUser()
    getNonFriend();
    getFriendList();
    postTimeline();
    checkFriendStatus();
}