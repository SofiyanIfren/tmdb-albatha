import * as React from 'react';

import { List, Grid, Container, Button, ButtonGroup } from '@mui/material';

import SearchBarComponent from '../SearchBarComponent/SearchBarComponent';
import BookingComponent from '../BookingComponent/BookingComponent';

import { BOOKING_SERVER_URL, ADMINISTRATION_SERVER_URL } from "../Utils/Constants";

export default function AdminPageComponent() {

    const { search } = window.location;
    const query = new URLSearchParams(search).get('s');

    const [searchQuery, setSearchQuery] = React.useState(query || '');
    const [answer, setAnswer]           = React.useState([]);
    const [url, setUrl]                 = React.useState([]);
    const [bookings, setBookings]       = React.useState([]);

    const [fakes, setFakes]             = React.useState([]);
    async function createFakeDataOnApi(){ 
        await fetch(ADMINISTRATION_SERVER_URL+"/fake/create")
            .then(response => {
                if (response.status === 200){
                    setFakes("Fake data Created. Don't forget to delete");
                    initBookings();
                } else {
                    setFakes("Ooops ! Something went wrong...\nAren't the fake data already present in database ?");
                    initBookings();
                }                            
            })
    }
    async function deleteFakeDataOnApi(){ 
        await fetch(ADMINISTRATION_SERVER_URL+"/fake/delete")
            .then(response => {
                if (response.status === 200){
                    setFakes("Fake data deleted with success");
                    initBookings();
                } else {
                    setFakes("Ooops ! Something went wrong...");
                    initBookings();
                }
            })
    }

    const initBookings = () => {
        fetch(BOOKING_SERVER_URL+"/all")
            .then(response => response.json())
            .then(data => setBookings(data));
    }
    React.useEffect(() => initBookings, [])

    return (
        <div>
            <Grid container spacing={2}>
                <Grid item xs={6}>
                    <Container sx={{ width: '100%' }}>
                        <SearchBarComponent searchQuery={searchQuery} setSearchQuery={setSearchQuery} 
                            answer={answer} setAnswer={setAnswer} url={BOOKING_SERVER_URL+"/search/"} setUrl={setUrl}/>
                        <List>
                            {answer.length > 0 
                            ? <BookingComponent answer={answer} />
                            : <BookingComponent answer={bookings} />
                        }
                        </List>
                    </Container>
                </Grid>
                <Grid item xs={4}>
                    <Container sx={{ width: '100%' }}>
                        <ButtonGroup sx={{ width: '100%', mt: 7 }}
                            orientation="vertical" aria-label="vertical outlined button group" variant="text">
                            <Button key="create" onClick={()=>createFakeDataOnApi()}>Create Test Data</Button>
                            <Button key="delete" onClick={()=>deleteFakeDataOnApi()}>Remove Test Data</Button>
                        </ButtonGroup>
                        <Container sx={{ width: '100%', mt: 2}}>
                            {fakes}
                        </Container>
                    </Container>
                </Grid>
            </Grid>
        </div>
      )
}
