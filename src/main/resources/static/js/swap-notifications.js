window.unreadSwaps = new Set();

function subscribeToAllSwaps() {
    if (!ws || !ws.stompClient || !ws.connected) {
        setTimeout(subscribeToAllSwaps, 500);
        return;
    }
    if (!window.mySwapIds) return;
    window.mySwapIds.forEach((swapId) => {
        ws.stompClient.subscribe(`/topic/swap/${swapId}`, (message) => {
            const messageData = JSON.parse(message.body);
            onSwapMessage(swapId, messageData);
        });
    });
}

document.addEventListener("DOMContentLoaded", subscribeToAllSwaps);

function updateNavbarUnreadBadge() {
    const badge = document.getElementById("nav-unread");
    const count = window.unreadSwaps.size;
    if (badge) {
        if (count > 0) {
            badge.style.display = "inline-block";
            badge.textContent = count;
        } else {
            badge.style.display = "none";
        }
    }
}

function onSwapMessage(swapId, messageData) {
    if (messageData.from !== config.username && (!isOnSwapsScreen() || !isCurrentSwapOpen(swapId))) {
        window.unreadSwaps.add(swapId);
        updateNavbarUnreadBadge();
    }
}

function markSwapAsRead(swapId) {
    window.unreadSwaps.delete(swapId);
    updateNavbarUnreadBadge();
}

function onEnterSwapsScreen() {
    window.unreadSwaps.clear();
    updateNavbarUnreadBadge();
}

function isOnSwapsScreen() {
    return window.location.pathname.startsWith("/swaps");
}

function isCurrentSwapOpen(swapId) {
    return window.currentSwapId === swapId;
}
