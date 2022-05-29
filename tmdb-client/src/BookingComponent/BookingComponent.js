import * as React from 'react';
import convertToReadableDate from '../Utils/Functions';

import { List, Card, CardHeader, Typography, CardContent,
    ButtonGroup, Button, IconButton, Snackbar } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { BOOKING_SERVER_URL, MOVIE_SERVER_URL } from "../Utils/Constants";

export default function BookingComponent(props) {
    
    const BookingMovie = ({movieId}) => {
        const [details, setDetails] = React.useState(false);
        React.useEffect(() => {
            fetch(MOVIE_SERVER_URL+"/details/"+movieId)
                .then(response => response.json())
                .then(data => {
                    setDetails(data);
                    return (data.title);
                })
            }, [])
        return (
            <CardHeader title={details.title} subheader={details.tagline}/>
        )
    }

    const [open, setOpen] = React.useState(false);
    const [message, setMessage] = React.useState('');
    const handleClick = () => { setOpen(true); }
    const handleClose = (event, reason) => {
        if (reason === 'clickaway') { return; }
        setOpen(false);
    }
    const action = (
        <React.Fragment>
          <IconButton size="small" aria-label="close" color="inherit" onClick={handleClose}>
            <CloseIcon fontSize="small" />
          </IconButton>
        </React.Fragment>
    )
    async function cancelBooking(bookingId){
        fetch(BOOKING_SERVER_URL+"/cancel/"+bookingId, {method: 'PUT'}).then(response => {
            if (response.status === 200){
                setMessage('Booking canceled with success !');
                handleClick();
                setTimeout(() => window.location.reload(false), 1000);
            } else {
                setMessage('Ooops ! Something went wrong...');
                handleClick();
            }
        })
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
                                {book.active
                                ? <Button onClick={()=>(cancelBooking(book.id))}>Cancel Booking</Button>
                                : '' } {/* Booking is not active : only update allowed */}
                                <Button onClick={()=>(console.log('update'))}>Update Booking</Button>
                                <Snackbar
                                    open={open}
                                    autoHideDuration={6000}
                                    onClose={handleClose}
                                    message={message}
                                    action={action}
                                />
                            </ButtonGroup>
                        </CardContent>
                    </Card>
                ))}
            </List>
        </div>
    )

}