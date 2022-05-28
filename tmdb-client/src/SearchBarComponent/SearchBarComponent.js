import * as React from 'react';

import TextField from "@mui/material/TextField";

const SearchBarComponent = ({searchQuery, setSearchQuery, answer, setAnswer, url, setUrl}) => {
    
    async function fetchApi(keyword, url){
        if (keyword !== '') { // if empty string, no api call is made
            const response = await fetch(
                url+keyword
            ).then(response => response.json());
            setAnswer(response);
        } else {
            setAnswer([]);
        }
    }

    return (
        <form onSubmit={(e) => e.preventDefault()}>
            <TextField
                onInput={(e) => {
                    e.preventDefault();
                    setSearchQuery(e.target.value);
                    fetchApi(e.target.value, url);
                }}
                variant="outlined"
                placeholder="Search..."
                size="small"
                value={searchQuery}
                />
        </form>      
    );
}

export default SearchBarComponent;