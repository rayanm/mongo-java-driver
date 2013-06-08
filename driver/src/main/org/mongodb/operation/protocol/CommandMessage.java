/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.operation.protocol;

import org.bson.io.OutputBuffer;
import org.mongodb.Document;
import org.mongodb.Encoder;
import org.mongodb.command.Command;

public class CommandMessage extends BaseQueryMessage {
    private final Command commandOperation;
    private final Encoder<Document> encoder;

    public CommandMessage(final String collectionName, final Command commandOperation, final Encoder<Document> encoder,
                          final MessageSettings settings) {
        super(collectionName, settings);
        this.commandOperation = commandOperation;
        this.encoder = encoder;
    }

    @Override
    protected RequestMessage encodeMessageBody(final OutputBuffer buffer, final int messageStartPosition) {
        writeQueryPrologue(commandOperation, buffer);
        addDocument(commandOperation.toDocument(), encoder, buffer);
        return null;
    }
}
