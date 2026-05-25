document.getElementById("uploadForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        const templateSelect =
            document.getElementById("templateSelect");

        const fileInput =
            document.getElementById("excelFile");

        // Template Validation

        if (templateSelect.value === "" || templateSelect.value === "Choose a template") {

            alert("Please select a template.");

            return;
        }

        // File Validation

        if (fileInput.files.length === 0) {

            alert("Please select an Excel file.");

            return;
        }

        const formData = new FormData();

        // Send Template Name

        formData.append(
            "template",
            templateSelect.value
        );

        // Send File

        formData.append(
            "file",
            fileInput.files[0]
        );

        try {

            const response =
                await fetch("/api/temp/excel", {

                    method: "POST",

                    body: formData
                });

            const result =
                await response.text();

            alert(result);

        } catch (error) {

            console.log(error);

            alert("❌ Server Error!");
        }

    });

// function showSuccess(text){
//
//     const message =
//         document.getElementById("message");
//
//     message.style.display = "block";
//
//     message.className = "success";
//
//     message.innerText = text;
// }
//
// function showError(text){
//
//     const message =
//         document.getElementById("message");
//
//     message.style.display = "block";
//
//     message.className = "error";
//
//     message.innerText = text;
// }


// function displayTable(data){
//
//     const resultDiv =
//         document.getElementById("message");
//
//     resultDiv.innerHTML = "";
//
//     let table = "<table>";
//
//     data.forEach((row, rowIndex) => {
//
//         table += "<tr>";
//
//         row.forEach(cell => {
//
//             if(rowIndex === 0){
//
//                 table += `<th>${cell}</th>`;
//
//             }else{
//
//                 table += `<td>${cell}</td>`;
//             }
//
//         });
//
//         table += "</tr>";
//
//     });
//
//     table += "</table>";
//
//     resultDiv.innerHTML = table;
// }