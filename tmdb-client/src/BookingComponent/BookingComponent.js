import * as React from 'react';
import convertToReadableDate from '../Utils/Functions';

import { List, Card, CardHeader, Typography, CardContent,
    ButtonGroup, Button } from '@mui/material';
import { MOVIE_SERVER_URL } from "../Utils/Constants";

export default function BookingComponent(props) {
    
    
    const BookingMovie = ({movieId}) => {
        const [details, setDetails] = React.useState(false);
        React.useEffect(() => {
            fetch(MOVIE_SERVER_URL+"/details/"+movieId)
                .then(response => response.json())
                .then(data => {
                    console.log(data.title);
                    setDetails(data)
                    return (data.title);
                })
            }, [])
        return (
            <CardHeader title={details.title} subheader={details.tagline}/>
        )
    }

    return (
        <div>
            <List>
                {props.answer.map((book) => (
                    <Card container sx={{ display: 'column', mt: 1 }} key={book.id}>
                        <BookingMovie movieId={book.movieId} />
                        <CardContent>
                            <Typography variant="body2" color="text.secondary">
                                <strong>Booked on : </strong>{convertToReadableDate(book.date, "booking")}<br/>
                                <strong>By : </strong>{book.user.firstName} {book.user.lastName}<br/>
                                <strong>Email : </strong>{book.user.email}
                            </Typography>
                            <ButtonGroup variant="text" sx={{ mt: 2 }}>
                                <Button onClick={()=>(console.log('cancel'))}>Delete Booking</Button>
                                <Button onClick={()=>(console.log('update'))}>Update Booking</Button>
                            </ButtonGroup>
                        </CardContent>
                    </Card>
                ))}
            </List>
        </div>
    )

}