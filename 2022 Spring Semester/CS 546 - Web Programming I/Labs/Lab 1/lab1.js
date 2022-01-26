const questionOne = function questionOne(arr) {
    let ans = 0;
    for (let i = 0; i < arr.length; i++) {
        ans += arr[i]*arr[i];
    }
    return ans; 
}

const questionTwo = function questionTwo(num) { 
    if (num < 1) {
        return 0;
    }
    if (num === 1) {
        return 1;
    }
    let F0 = 0;
    let F1 = 1;
    let nextTerm;
    for (let i = 0; i <= num - 2; i++) {
        nextTerm = F0 + F1;
        F0 = F1;
        F1 = nextTerm;
    }
    return nextTerm;
}

const questionThree = function questionThree(text) {
    let lower = text.toLowerCase();
    let ans = 0;
    for (let i = 0; i < lower.length; i++) {
        if (lower[i] === 'a' || lower[i] === 'e' || lower[i] === 'i' || lower[i] === 'o' || lower[i] === 'u'){
            ans++;
        }
    }
    return ans;
}

const questionFour = function questionFour(num) {
    if (num < 0) {
        return NaN;
    }
    if (num === 0) {
        return 1;
    }
    return num*questionFour(num-1);
}

module.exports = {
    firstName: "Daksh", 
    lastName: "Bhuva", 
    studentId: "10475468",
    questionOne,
    questionTwo,
    questionThree,
    questionFour
};
