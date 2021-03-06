import React, { useEffect, useState } from 'react'
import { Themed } from "theme-ui"

export default function PostDate (props) {

    const [views, setViews] = useState('...');

    useEffect(() => {
        const [,postName] = window.location.href.match(/\/([a-zA-Z-]+)\//);
        fetch(`https://api.countapi.xyz/hit/blog-androz2091/${postName}`).then((res) => {
            res.json().then((data) => {
                setViews(data.value);
            });
        });
    }, [])

    return (
        <>
        <Themed.p
            style={{ display: 'inline' }}
            sx={{
            fontSize: 1,
            mt: -3,
            mb: 3,
            }}
            {...props}
        />
        <p style={{ display: 'inline' }}> • {views} views</p>
        </>
    )
}
