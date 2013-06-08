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
import org.mongodb.operation.BaseUpdate;
import org.mongodb.operation.Replace;

public class ReplaceMessage<T> extends BaseUpdateMessage {
    private Replace<T> replace;
    private final Encoder<T> encoder;

    public ReplaceMessage(final String collectionName, final Replace<T> replace,
                          final Encoder<Document> baseEncoder, final Encoder<T> encoder, final MessageSettings settings) {
        super(collectionName, OpCode.OP_UPDATE, baseEncoder, settings);
        this.replace = replace;
        this.encoder = encoder;
    }

    @Override
    protected RequestMessage encodeMessageBody(final OutputBuffer buffer, final int messageStartPosition) {
        writeBaseUpdate(buffer);
        addDocument(replace.getReplacement(), encoder, buffer);
        return null;
    }

    @Override
    protected BaseUpdate getUpdateBase() {
        return replace;
    }
}
