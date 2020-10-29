document.addEventListener('DOMContentLoaded', () => {
    item = 1;

    function addIngredient() {
        div = document.getElementById("items");
        console.log("so" + item)
        item++;
        newitem = "<input type=\"text\" name=\"item" + item + "\"size=\"45\">";
        newitem += "<input type=\"text\" name=\"amount" + item + "\"size=\"15\">";
        newnode = document.createElement("div");
        newnode.innerHTML = newitem;
        div.insertBefore(newnode, button);
    }

    button = document.getElementById("add");
    button.addEventListener("click", () =>  {
        console.log(item);
        addIngredient(button);
    })
})