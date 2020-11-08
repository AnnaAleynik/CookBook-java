document.addEventListener('DOMContentLoaded', () => {
    tag = 2;

    function addTag() {
        div = document.getElementById("tags");
        newitem = "<div class=\"input-group mb-2 mt-2\">";
        newitem += "<input class=\"form-control col-1 mr-3 form-control\" type=\"text\" name=\"tag" + tag + "\" placeholder=\"Тег\">";
        newnode = document.createElement("div");
        newnode.innerHTML = newitem;
        div.insertBefore(newnode, buttonTag);
        amount = document.getElementById("amountTag");
        amount.setAttribute("value", tag);
        tag++;
    }

    buttonTag = document.getElementById("addTag");
    buttonTag.addEventListener("click", () => {
        addTag(buttonTag);
    });
});