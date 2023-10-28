function dateToDays(date){
    const arr = date.split('.');
    const [year, month, day] = arr.map(Number);

    const days = ((year - 2000) * 12 * 28) + (month * 28) + day;
    return days;
}

function solution(today, terms, privacies) {
    const termMap = new Map();
    terms.forEach(term => {
        const [alpha, mon] = term.split(' ');
        termMap.set(alpha, mon);    
    });

    const todayDays = dateToDays(today);
    var answer = [];
    privacies.forEach((privacie, idx) => {
       const [date, alpha] = privacie.split(' ');
       const dateDays = dateToDays(date);
       const addDays = termMap.get(alpha) * 28 - 1;
       // console.log(todayDays, dateDays, addDays, idx);
       if(todayDays > dateDays + addDays){
           answer.push(idx + 1);
       }
    });
    return answer;
}
