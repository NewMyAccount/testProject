/**
 * 一级评论
 */
function post() {
    let questionId = $("#id").val();
    let content = $("#comment-content").val();
    console.log(content);
    console.log(questionId);
    if (!content) {
        alert("评论内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "type": 1,
            "content": content
        }),
        success: function (response) {
            console.log(response);
            if (response.code === 200) {
                window.location.reload();
                // $("#comment-person").hide();
            } else if (response.code === 3) {
                let isAccepted = confirm(response.message);
                if (isAccepted) {
                    window.open("https://github.com/login/oauth/authorize?client_id=e6dedd08826359276765&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                    window.localStorage.setItem("closable", "true");
                }
            } else {
                alert(response.message);
            }
        },
        dataType: "json"
    })
}

/**
 * 展开二级评论
 */
function comment(e) {
    let id = e.getAttribute("data-id");
    $("#comment-" + id).toggle("in");
    e.classList.toggle("active");
}