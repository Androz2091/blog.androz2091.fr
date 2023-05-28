import React, { useEffect, useState } from 'react'
import { Themed } from "theme-ui"

export default function PostDate (props) {

    const [viewsContent, setViewsContent] = useState('...');

    useEffect(() => {
        const abortController = new AbortController();
        setTimeout(() => {
            abortController.abort();
        }, 2000);
        const [,postName] = window.location.href.match(/\/([a-zA-Z-0-9]+)\//);
        fetch(`https://api.countapi.xyz/hit/blog-androz2091/${postName}`, {
            signal: abortController.signal
        }).then((res) => {
            res.json().then((data) => {
                setViewsContent(data.value);
            });
        }).catch(() => {
            setViewsContent('API is offline (views can not be retrieved)');
        });
    }, [])

    return (
        <>
        <script async defer data-website-id="c7374106-e054-4215-b618-2814e7f85f2a" src="https://analytics.androz2091.fr/umami.js"></script>
        <Themed.p
            style={{ display: 'inline' }}
            sx={{
                fontSize: 1,
                mt: -3,
                mb: 3,
            }}
            {...props}
        />
        <Themed.p
            style={{ display: 'inline' }}
            sx={{
                fontSize: 1,
                mt: -3,
                mb: 3,
            }}
            {...props}>
            &nbsp;• {viewsContent}
        </Themed.p>
        </>
    )
}
