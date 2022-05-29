import * as React from 'react';

import { Button, Grid, List, Typography, Modal, Box, FormControl, TextField, FormLabel,
    IconButton, Snackbar } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { MOVIE_SERVER_URL, USER_SERVER_URL } from "../Utils/Constants";

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
        alignItems: 'center',
        '& .MuiTextField-root': { m: 1 }
    }
    const [showModal, setShowModal] = React.useState(false);
    const handleOpenModal = () => {
        setShowModal(true)
    }
    const handleCloseModal = () => {
        setShowModal(false);
    }

    const [open, setOpen ] = React.useState(false);
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

    const [ email, setEmail ] = React.useState('');
    const [ firstname, setFirstname ] = React.useState('');
    const [ lastname, setLastname ] = React.useState('');
    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(`The email you entered was: ${email}`)
        console.log(`The firstname you entered was: ${firstname}`)
        console.log(`The lastname you entered was: ${lastname}`)
        const user = {
            email: `${email}`,
            firstName: `${firstname}`,
            lastName: `${lastname}`
        }
        fetch(USER_SERVER_URL+"/create", {method: 'POST',
            body: JSON.stringify(user), 
            headers: {
                'Content-Type': 'application/json'
            }}).then(response => {
                if (response.status === 200){
                    setMessage('User created with success !');
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
            <Grid container spacing={2}>
            <Grid item xs={10}>
                <Typography xs gutterBottom variant="h3" component="div" sx={{ mt: 1 }}>Welcome home !</Typography>
            </Grid>
            <Grid item xs={2}>
                <Button onClick={handleOpenModal}>
                    <Typography xs gutterBottom variant="h5" component="div" sx={{ mt: 1 }}>Register</Typography>
                </Button>
            </Grid>
            </Grid>
            <SearchBarComponent 
                searchQuery={searchQuery}   setSearchQuery={setSearchQuery} 
                answer={answer}             setAnswer={setAnswer} 
                url={MOVIE_SERVER_URL+"/search/"} setUrl={setUrl}/>
            <List>
                {answer.length > 0 
                    ? <MovieComponent discover={answer} />
                    : <MovieComponent discover={discover} />
                }
            </List>
            <Modal
                open={showModal}
                onClose={handleCloseModal}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description">
                <Box sx={modalStyle}>
                    <form onSubmit={handleSubmit}>
                        <FormControl sx={{ width: '100%' }}>
                            <FormLabel sx={{ mb: 1 }} component="legend">Register</FormLabel>
                            <TextField type="text" value={email} onChange={(e) => setEmail(e.target.value)}/>
                            <TextField type="text" value={firstname} onChange={(e) => setFirstname(e.target.value)}/>
                            <TextField type="text" value={lastname} onChange={(e) => setLastname(e.target.value)}/>
                            <Button type="submit">Submit</Button>
                        </FormControl>
                    </form>
                </Box>
            </Modal>
            <Snackbar
                open={open}
                autoHideDuration={6000}
                onClose={handleClose}
                message={message}
                action={action}
            />
        </div>
    );
}
