import {Grid, Paper} from "@mui/material";
import {styled} from "@mui/material/styles";
import classes from "./ParkingGrid.module.css"

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
}))
export default function ParkingGrid(props) {
    return (
        <Paper sx={{ borderRadius: '16px', boxShadow: 3}} style={{backgroundColor: "#1D1D1D" }} className={classes.box}>
            <Grid className={classes.grid} container rowSpacing={3} columnSpacing={4}>
                {props.parkingSpots.map((spot, index) =>
                    <Grid key={index} item xs={3}>
                        <Item style={{
                            color: spot.status ? "green" : "red",
                            backgroundColor: spot.status ? "white" : "#ededed"
                        }}>
                            {spot.id}
                        </Item>
                    </Grid>
                )}
            </Grid>
        </Paper>
    )
}