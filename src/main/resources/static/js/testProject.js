/**
 * 添加一级评论
 */
function comment() {
    //问题的id
    let questionId = $("#id").val();
    //问题的评论内容
    let content = $("#comment-content").val();
    console.log(content);
    console.log(questionId);
    post(questionId, 1, content);
}

/**
 * 添加二级评论
 */
function reply(e) {
    //一级评论的id
    let commentId = e.getAttribute("data-id");
    //发布的二级评论内容
    let content = $("#reply-" + commentId).val();
    post(commentId, 2, content);
}


function post(id, type, content) {
    if (!content) {
        alert("评论内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": id,
            "type": type,
            "content": content
        }),
        success: function (response) {
            console.log(response);
            if (response.code === 200) {
                //重新请求页面
                window.location.reload();
                // $("#comment-person").hide();
            } else if (response.code === 3) {
                //code === 3，未登录
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
function commentList(e) {
    //评论的id
    let id = e.getAttribute("data-id");
    //toggleClass会给collapse添加删除in字段来展开折叠二级评论
    let commentFather = $("#comment-" + id).toggleClass("in");
    //给评论按钮添加高亮,原生js的写法
    e.classList.toggle("active");
    if (commentFather.hasClass("in")) {
        $.getJSON("/comment/" + id, function (data) {
            //data是调用get请求返回的json数据
            console.log(data.data);
            //return保证多次点击不会重复加载数据
            if ($("#comment-" + id).children().length > 1) {
                return;
            }
            //each遍历data的每一个数据，functionde key是data的下标，value是值。
            $.each(data.data.reverse(), function (key, value) {
                console.log(key)
                console.log(value);
                //left部分
                let a = $("<a/>", {
                    "href": "/questionDetail/" + $("#id").val(),
                }).append($("<img/>", {
                    "class": "img-rounded comment-avatar",
                    "src": value.user.avatarUrl,
                    "alt": ""
                }))

                let mediaLeft = $("<div/>", {
                    "class": "media-left",
                }).append(a);

                //media body部分
                let h6 = $("<h6/>", {
                    "class": "media-heading",
                    "html": value.user.name
                })
                let contentElement = $("<div/>").append($("<span/>", {
                    "html": value.content
                }))

                let menu = $("<div/>").append($("<span/>", {
                    "class": "pull-right",
                    "html": moment(value.gmtCreate).format("YYYY-MM-DD")
                }))
                let mediaBody = $("<div/>", {
                    "class": "media-body",
                });
                mediaBody.append(h6).append(contentElement).append(menu);

                let media = $("<div/>", {
                    "class": "media",
                });
                media.append(mediaLeft);
                media.append(mediaBody);
                let comments = $("<div/>", {
                    "class": "comments",
                });
                comments.append(media);
                commentFather.prepend(comments);
            });
        });
    }
}

function showTags() {
    $(".question-tagCategory").show();
}

function selectTag(e) {
    //标签原有的值
    let current = $("#tag").val();
    console.log(current);
    if (current) {
        //多次点击同一个标签，保证只有一个。
        if (current.indexOf(e.getAttribute("data-tag")) == -1) {
            $("#tag").val(current + ',' + e.getAttribute("data-tag"));
        } else {
            //已经存在了，就直接返回，什么都不做。
            return
        }
    } else {
        $("#tag").val(e.getAttribute("data-tag"));
    }
}