import DashboardIcon from '@mui/icons-material/Dashboard';
import {Grid, Paper} from "@mui/material";
import IconTextButton from "../UI/IconTextButton";
import MessageIcon from '@mui/icons-material/Message';
import AnalyticsIcon from '@mui/icons-material/Analytics';

const Navbar = (props) => {
    return (
        <Paper elevation={3}>
            <Grid container columnSpacing={3} rowSpacing={3} justifyContent={"center"} paddingBottom={"1rem"}>
                <Grid item xs={2}>
                    <IconTextButton text={"Dashboard"} icon={<DashboardIcon fontSize={"large"}/>}/>
                </Grid>
                <Grid item xs={2}>
                    <IconTextButton text={"Statistics"} icon={<AnalyticsIcon fontSize={"large"}/>}/>
                </Grid>
                <Grid item xs={2}>
                    <IconTextButton text={"Messages"} icon={<MessageIcon fontSize={"large"}/>}/>
                </Grid>
            </Grid>
        </Paper>
    )
}
export default Navbar;