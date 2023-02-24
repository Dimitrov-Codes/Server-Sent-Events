import {Button, Typography} from "@mui/material";

export default function IconTextButton(props) {
    return (
            <Button style={{color:"#143D59"}}>
                {props.icon}
                <Typography variant={"button"}>
                    {props.text}
                </Typography>
            </Button>
    );
}

