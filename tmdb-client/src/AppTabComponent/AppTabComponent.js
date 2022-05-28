import * as React from 'react';

import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

import MainPageComponent from '../MainPageComponent/MainPageComponent';
import AdminPageComponent from '../AdminPageComponent/AdminPageComponent';

function TabPanel(props) {
  
    const { children, value, index, ...other } = props;

    return (
        <div role="tabpanel" hidden={value !== index} id={`simple-tabpanel-${index}`} aria-labelledby={`simple-tab-${index}`} {...other}>
            {value === index && (
                <Box sx={{ p: 3 }}>
                    <Typography component={'span'}>{children}</Typography>
                </Box>
            )}
        </div>
    );
}

export default function AppTabComponent() {

    const [value, setValue] = React.useState(0);
    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <Box sx={{ width: '100%' }}>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                    <Tab label="Main Page"/>
                    <Tab label="Admin Page"/>
                </Tabs>
            </Box>
            <TabPanel value={value} index={0}>
                <div>
                    <MainPageComponent /> 
                </div>
            </TabPanel>
            <TabPanel value={value} index={1}>
                <div>
                    <AdminPageComponent />
                </div>
            </TabPanel>
        </Box>
    );
}
