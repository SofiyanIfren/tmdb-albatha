const months = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];
        
export default function convertToReadableDate(date, context){
    let day = new Date(date).getDay();
    let month = months[new Date(date).getMonth()];
    let year = new Date(date).getUTCFullYear();
    if (context === "movie")    return month+' '+year;
    if (context === "booking")  return day+' '+month+' '+year;
}