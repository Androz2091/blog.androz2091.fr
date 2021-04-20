import React from "react"

export default function NotFound() {
  return (
    <div style={{
        height: '100vh',
        display: 'grid',
        placeItems: 'center'
    }}>
        <div>
            <h2>Page not found</h2>
            <p>The page you are requesting does not exist on the server...</p>
        </div>
    </div>
  )
}
