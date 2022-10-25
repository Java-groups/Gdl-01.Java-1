'use strict';

const openClosed = document.querySelector('#openClosedContainer');
const newSurvey = document.querySelector('.new-survey');
const newSurveyContainer = document.querySelector('.new-survey-container');
let counter = 1;

loadEventListeners();

function loadEventListeners() {
    openClosed.addEventListener('click', switchSurveys);
    newSurvey.addEventListener('click', createNewSurvey);
    newSurveyContainer.addEventListener('click', addAnswerOption);
}

function createNewSurvey() {
    clearPollInfoContainer();
    let newPollForm = document.createElement('form');

    newPollForm.innerHTML = `
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Question</label>
            <textarea class="form-control" id="textQuestion" rows="3"></textarea>
        </div>
        <div class="input-group is-invalid option-container">
            <div class="option">
                <div class="custom-file">
                    <input type="text" id="${counter}" required>
                    <button class="btn btn-outline-secondary" type="button" id="delete-element"> X </button>
                </div>
            </div>
        </div>
        <button type="button" class="btn btn-outline-danger add-option">+Add</button>
        <div>
            <button type="button" class="btn btn-outline-danger">Cancel</button>
            <button type="button" class="btn btn-danger">Save</button>        
        </div>
    `;

    newSurvey.disabled = true;
    newSurveyContainer.appendChild(newPollForm);
}

function addAnswerOption(e) {
    if (e.target.classList.contains('add-option')){
        counter++;
        let optionContainer = document.querySelector('.option-container');
        let newOption = document.createElement('div');

        newOption.setAttribute('class', 'option');
        newOption.innerHTML = `
            <div class="custom-file">
                <input type="text" id="${counter}" required>
                <button class="btn btn-outline-secondary" type="button" id="delete-element"> X </button>
            </div>
        `;

        optionContainer.appendChild(newOption);
    }

}

function switchSurveys(e){
    if (e.target.classList.contains('openedSurveyBtn')){
        showOpenedSurveys();
    } else if (e.target.classList.contains('closedSurveyBtn')){
        showClosedSurveys();
    }
}

function showOpenedSurveys() {
    cleanHTML();

    let tableHeader = document.querySelector('.card-body table thead');
    let trow = document.createElement('tr');

    trow.innerHTML = `
        <th class="font-weight-bold text-gray-800">Question</th>
        <th class="font-weight-bold text-gray-800">Status</th>
        <th class="font-weight-bold text-gray-800">Responses</th>
    `;

    tableHeader.appendChild(trow);
}

function showClosedSurveys() {
    cleanHTML();

    let tableHeader = document.querySelector('.card-body table thead');
    let trow = document.createElement('tr');

    trow.innerHTML = `
        <th class="font-weight-bold text-gray-800">Question</th>
        <th class="font-weight-bold text-gray-800">Closed Date</th>
        <th class="font-weight-bold text-gray-800">Responses</th>
    `;

    tableHeader.appendChild(trow);
}

function cleanHTML(){
    let theader = document.querySelector('.card-body table thead');
    while (theader.firstChild){
        theader.removeChild(theader.firstChild);
    }
}

function clearPollInfoContainer() {
    let pollInfoContainer = document.querySelector('#poll-info-container');

    while (pollInfoContainer.hasChildNodes()) {
        if (pollInfoContainer.firstElementChild.getAttribute('class') !== 'new-survey-container') {
            pollInfoContainer.removeChild(pollInfoContainer.firstElementChild);
        } else {
            break;
        }
    }
}
