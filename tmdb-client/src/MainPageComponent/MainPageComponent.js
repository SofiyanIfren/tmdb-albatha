import * as React from 'react';

import { List, Typography } from '@mui/material';
import { MOVIE_SERVER_URL } from "../Utils/Constants";

import SearchBarComponent from '../SearchBarComponent/SearchBarComponent';
import MovieComponent from '../MovieComponent/MovieComponent';

export default function MainPageComponent() {

    const { search } = window.location;
    const query = new URLSearchParams(search).get('s');

    const [searchQuery, setSearchQuery] = React.useState(query || '');
    const [answer, setAnswer]           = React.useState([]);
    const [url, setUrl]                 = React.useState([]);
    const [discover, setDiscover]       = React.useState([]);

    React.useEffect(() => {
        fetch(MOVIE_SERVER_URL+"/discover")
            .then(response => response.json())
            .then(data => setDiscover(data));
    }, [])
        
    return (
        <div>
            <Typography gutterBottom variant="h3" component="div" sx={{ mt: 1 }}>
                Welcome home !
            </Typography>
            <SearchBarComponent 
                searchQuery={searchQuery}   setSearchQuery={setSearchQuery} 
                answer={answer}             setAnswer={setAnswer} 
                url={MOVIE_SERVER_URL+"/search/"} setUrl={setUrl}/>
                <List>{answer.length > 0 
                    ? <MovieComponent discover={answer} />
                    : <MovieComponent discover={discover} />
                }</List>
        </div>
    );
}
