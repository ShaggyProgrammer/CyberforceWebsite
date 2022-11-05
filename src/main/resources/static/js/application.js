$(document).ready(function(){

    console.log("hello");



})

async function login(){

    console.log("Attempted login");

    let formData = JSON.stringify($("#loginform").serializeArray());

    console.log(formData);

    $.ajax({
      type: "POST",
      url: "/form/login",
      data: formData,
      success: async function(data){
        console.log(data);
      },
      dataType: "json",
      contentType : "application/json"
    });
}

(function() {

    let form = document.getElementById("contactform");
    let inputfile = document.getElementById("file");



    const formData = new FormData();

    const handleSubmit = (event) => {
        event.preventDefault();

        console.log(inputfile);

        for (const file of inputfile.files) {
            formData.append("file", file);
        }

        formData.append("name", "name");

        formData.append("phone", "name");

        formData.append("email", "name");

        fetch("/form/contact", {
            method: "post",
            body: formData,
        }).catch((err) => err).then(() => {
            alert("Successfully uploaded file to server and send email");
        });


    }

    console.log(formData);

    form.addEventListener("submit", handleSubmit);
})();