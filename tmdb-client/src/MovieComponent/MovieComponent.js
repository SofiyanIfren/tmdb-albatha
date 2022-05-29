import * as React from 'react';
import convertToReadableDate from '../Utils/Functions';

import { List, Card, CardHeader, CardMedia, CardContent, Typography,
    ButtonGroup, Button, Modal, Box } from '@mui/material';
import { IMAGE_BASE_URL, POSTER_SIZE, MOVIE_SERVER_URL } from "../Utils/Constants";

export default function MovieComponent(props) {

    const modalStyle = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };
    
    const [open, setOpen] = React.useState(false);
    const [details, setDetails] = React.useState(false);
    async function handleOpen(movieId){
        fetch(MOVIE_SERVER_URL+"/details/"+movieId)
            .then(response => response.json())
            .then(data => {
                setDetails(data);
                setOpen(true); // add param + make an api call to return data in the state (TRAILER to find + add)
            })
        
    }
    const handleClose = () => setOpen(false); // flush data in the state

    return (
        <div>
            <List>
                {props.discover.map((discoveredMovie) => (
                    <Card container sx={{ display: 'flex', mt: 1 }} key={discoveredMovie.id}>
                        <CardMedia
                            component="img"
                            sx={{ width: 151 }}
                            image={`${IMAGE_BASE_URL}${POSTER_SIZE}${discoveredMovie.poster_path}`}
                            alt={discoveredMovie.original_title}
                        />
                        <CardContent item xs={8}>
                            <CardHeader 
                                title={discoveredMovie.title}
                                subheader={convertToReadableDate(discoveredMovie.release_date, "movie")}
                            />
                            <Typography variant="body2" color="text.secondary">
                                <strong>Rating : {discoveredMovie.vote_average}/10 </strong><br/><br/>
                                Overview : {discoveredMovie.overview}
                            </Typography>
                            <ButtonGroup variant="text" sx={{ mt: 2 }}>
                                <Button onClick={()=>handleOpen(discoveredMovie.id)}>Details</Button>
                                <Button onClick={()=>(console.log('booking'))}>Booking</Button>
                                <Button onClick={()=>(console.log('cancel'))}>Cancel Booking</Button>
                            </ButtonGroup>
                            <Modal
                                open={open}
                                onClose={handleClose}
                                aria-labelledby="modal-modal-title"
                                aria-describedby="modal-modal-description">
                                <Box sx={modalStyle}>
                                    <Typography id="modal-modal-title" variant="h6" component="h2">
                                        {details.original_title ? details.original_title : "Sorry, no title available for this movie"}
                                    </Typography>
                                    <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                                        {details.tagline ? details.tagline : "Sorry, no tagline available for this movie..."}
                                    </Typography>
                                    
                                </Box>
                            </Modal>
                        </CardContent>
                    </Card>
                ))}
            </List>
        </div>
    )
}