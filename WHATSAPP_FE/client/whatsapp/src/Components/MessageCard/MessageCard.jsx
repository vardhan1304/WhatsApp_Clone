import React, { startTransition } from "react";

const MessageCard = ({ isReqUserMEssage, content }) => {
  return (
    <div
      className={`py-2 px-2 rounded-md max-w-[50%] ${
        isReqUserMEssage ? "self-start bg-white" : "self-end bg-[#d9fdd3]"
      }`}
    >
      <p>{content}</p>
    </div>
  );
};

export default MessageCard;
