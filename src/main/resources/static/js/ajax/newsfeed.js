function addNameUser() {
    let content = "";
    let idUser = localStorage.getItem("accountId");
    console.log(idUser);

    $.ajax({
        type: "POST",
        url: `http://localhost:8080/users/find/${idUser}`,
        success: function (user) {
            // console.log(idUser)
            console.log(user.fullName);
            console.log(user.imgUrl)
            content += user.fullName;
            document.getElementById('nameUser').innerText = content;
            document.getElementById("imageNavbar").innerHTML = `<img src="${user.imgUrl}" style="width: 32px; height: 32px;border-radius: 100%" alt="">`
            document.getElementById("imagePost").innerHTML = `<img src="${user.imgUrl}" style="width: 60px; height: 60px;border-radius: 100%" alt="">`
        }
    })
}


function postNewsfeed() {
    let content = `<div class = "central-meta item" >
        <div class = "user-post">
        <div class = "friend-info" >
        <figure>
            <img src="../static/images/resources/friend-avatar10.jpg" alt="">
        </figure>
        <div class="friend-name">
        <ins><a href="time-line.html" title=""><span id="userPost"></span></a></ins>
        </div>
        <div class="post-meta">
        <img src="../static/images/resources/user-post.jpg" alt="">
            <div class="description">
                <p id="postContent">

                </p>
            </div>
            <div class="we-video-info">
                <ul>
                    <li>
					    <span class="comment" data-toggle="tooltip" title="Comments">
					    <i class="fa fa-comments-o"></i>
					    <ins>52</ins>
					    </span>
                    </li>
                    <li>
						<span class="like" data-toggle="tooltip" title="like">
						<i class="ti-heart">...</i>
						<ins>2.2k</ins>
						</span>
                    </li>
                </ul>
            </div>
    </div>
</div>
    <div class="coment-area">
        <ul class="we-comet">
            <li>
                <div class="comet-avatar">
                    <img src="../static/images/resources/comet-1.jpg" alt="">
                </div>
                <div class="we-comment">
                    <div class="coment-head">
                        <h5><a href="../time-line.html" title="">Jason borne</a></h5>
                        <a class="we-reply" href="#" title="Reply"><i class="fa fa-reply"></i></a>
                    </div>
                    <p class="userCommentContent">...</p>
                </div>
            </li>
        </ul>
    </div>
</div>
</div>`


}

function commentDetail() {

}

function likeInPostDetail() {

}

function likeInCommentDetail() {

}

addNameUser();