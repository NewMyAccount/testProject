function post() {
    var questionId = $("#id").val();
    var content = $("#comment-content").val();
    console.log(content);
    console.log(questionId);
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
        },
        dataType: "json"
    })
}