var flag = 1;
function resetForm()
{
    document.getElementById("enrollment-form").reset();
}
/**
 * This function checks the validation of name, email and website
 * based on some specific conditions
 */

function validateForm() {
    var username = document.getElementById("inputName").value;
    var email = document.getElementById("inputEmail").value;
    var website = document.getElementById("inputWebsite").value;
    var imageLink = document.getElementById("inputLink").value;
    flag = 1;
    const nameRegex = /^[A-Za-z\s\-']{2,50}$/;
    if (
        username == "" ||
        username.length < 2 ||
        nameRegex.test(username) == false ||
        email.indexOf("@") <= 0 ||
        (email.charAt(email.length - 4) != "." &&
            email.charAt(email.length - 3) != ".") ||
        (website.charAt(website.length - 5) != "." &&
            website.charAt(website.length - 4) != "." &&
            website.charAt(website.length - 3) != ".")||
        imageLink==""||
        (imageLink.match(/^http.*\.(jpeg|jpg|gif|png).*/)==null)
    ) {
        if (username == "") {
            document.getElementById("myName").innerHTML =
                "Please enter your name";
        } else if (username.length < 2) {
            document.getElementById("myName").innerHTML =
                "String more than 2";
        }
        else if (!nameRegex.test(username)) {
            document.getElementById("myName").innerHTML =
                "Please enter alphabetic character only";
        }
        else
            document.getElementById("myName").innerHTML = "";

        if (email.indexOf("@") <= 0) {
            document.getElementById("myEmail").innerHTML =
                "Invalid";
        } else if (
            email.charAt(email.length - 4) != "." &&
            email.charAt(email.length - 3) != "."
        ) {
            document.getElementById("myEmail").innerHTML =
                "invalid";
        }
        else document.getElementById("myEmail").innerHTML = "";

        if (
            website.charAt(website.length - 5) != "." &&
            website.charAt(website.length - 4) != "." &&
            website.charAt(website.length - 3) != "."
        ) {
            document.getElementById("myWeb").innerHTML =
                "invalid position";
        }
        else document.getElementById("myWeb").innerHTML = "";
        if(imageLink==""){
            document.getElementById("myImage").innerHTML =
                "invalid link";
        }
        else if(imageLink.match(/^http.*\.(jpeg|jpg|gif|png).*/)==null){
            document.getElementById("myImage").innerHTML =
                "invalid link";
        }
        else{
            document.getElementById("myImage").innerHTML = "";
        }
    }
    else {
        flag = 0;
        document.getElementById("myName").innerHTML = "";
        document.getElementById("myEmail").innerHTML = "";
        document.getElementById("myWeb").innerHTML = "";
        document.getElementById("myImage").innerHTML = "";
        
    }
   
}

/**
 * This function is responsible for the insertion and display of newly
 * enrolled students which is input by the user
 */
function sub() {
    if (flag == 0) {
        var name, email, website, imageLink, gender, indexValue = 0;
        name = document.getElementById('inputName').value;
        email = document.getElementById('inputEmail').value;
        website = document.getElementById('inputWebsite').value;
        imageLink = document.getElementById('inputLink').value;
        if (document.getElementById('genderType1').checked) {
            gender = document.getElementById('genderType1').value;
        }
        if (document.getElementById('genderType2').checked) {
            gender = document.getElementById('genderType2').value;
        }
        var skills = document.getElementsByName('mySkill[]');
        var len = skills.length;
        const skillArray = [];
        for (var i = 0; i < len; i++) {
            if (skills[i].checked) {
                skillArray[indexValue] = skills[i].value;
                indexValue = indexValue + 1;
            }
        }
        // Display Data
        var newTable = document.getElementById('TableScore');
        var row = newTable.insertRow(1);
        row.classList.add('animate')
        var cell2 = row.insertCell(0);
        var cell1 = row.insertCell(0);
        var description = "<b>" + name + "</b>" + "<br>" + gender + "<br>" + email + "<br>" + "<a href='" + website + "' target='_blank'>" + website + "</a>" + "<br>" + skillArray;
        cell2.innerHTML = "<td class=table table-striped table-bordered>" + "<img src='" + imageLink + "' height=150px, width=120px style=' object-fit: cover;'>" + "</td>";
        cell1.innerHTML = "<td >" + description + "</td>";
    }
}