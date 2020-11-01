document.addEventListener('DOMContentLoaded', () => {
    item = 4;

    function addIngredient() {
        div = document.getElementById("items");
        newitem = "<input type=\"text\" name=\"item" + item + "\"size=\"45\">";
        newitem += "<input type=\"text\" name=\"amount" + item + "\"size=\"15\">";
        newnode = document.createElement("div");
        newnode.innerHTML = newitem;
        div.insertBefore(newnode, button);
        amount = document.getElementById("amount");
        amount.setAttribute("value", item);
        console.log(amount.getAttribute("value"));
        item++;
    }

    button = document.getElementById("add");
    button.addEventListener("click", () =>  {
        addIngredient(button);

    })
})